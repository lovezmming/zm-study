package com.spring.service.elasticsearch.service;

import com.spring.common.entity.RecordSet;
import com.spring.common.util.ElasticSearchUtil;
import com.spring.common.util.JsonUtil;
import com.spring.model.entity.Partner;
import com.spring.model.response.UserBaseResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ElasticSearchService implements SearchService
{
    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void initSearchInfo(String userId, String index, String type, Map<String, Object> params) throws Exception
    {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userId", userId);
        paramsMap.put("type", Partner.INIT);
        ResponseEntity<UserBaseResponse> responseEntity = restTemplate.getForEntity("http://ZM-STUDY-DATA/partner/getPartnerByUserId?userId={userId}&type={type}", UserBaseResponse.class, paramsMap);
        UserBaseResponse responseBody = responseEntity.getBody();
        Map<String, Object> resultMap = responseBody.getResults();
        Map<String, Object> comment = JsonUtil.fromString((String) resultMap.get("comment"));
        TransportClient client = ElasticSearchUtil.connect((String)comment.get("ip"), (Integer)comment.get("port"));
        IndicesAdminClient adminClient = client.admin().indices();
        IndicesExistsResponse response = adminClient.exists(new IndicesExistsRequest().indices(new String[]{index})).actionGet();
        if (!response.isExists())
            adminClient.prepareCreate(index).get();
        client.prepareIndex(index, type).setSource(params).get();
        ElasticSearchUtil.destory(client);
    }

    @Override
    public RecordSet searchUserInfo(String userId, String index, String type, Map<String, Object> params) throws Exception
    {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userId", userId);
        paramsMap.put("type", Partner.INIT);
        ResponseEntity<UserBaseResponse> responseEntity = restTemplate.getForEntity("http://ZM-STUDY-DATA/partner/getPartnerByUserId?userId={userId}&type={type}", UserBaseResponse.class, paramsMap);
        UserBaseResponse responseBody = responseEntity.getBody();
        Map<String, Object> resultMap = responseBody.getResults();
        Map<String, Object> comment = JsonUtil.fromString((String) resultMap.get("comment"));
        TransportClient client = ElasticSearchUtil.connect((String)comment.get("ip"), (Integer)comment.get("port"));
        BoolQueryBuilder sexBoolQuery = QueryBuilders.boolQuery();
        sexBoolQuery.should(QueryBuilders.matchQuery("sex", params.get("sex")));
        BoolQueryBuilder nameBoolQuery = QueryBuilders.boolQuery();
        nameBoolQuery.should(QueryBuilders.matchQuery("name", params.get("name")));

        SearchResponse response = client.prepareSearch(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.boolQuery().must(nameBoolQuery).must(sexBoolQuery))
                .get();

        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : response.getHits().getHits())
        {
            Map<String, Object> result = new HashMap<String, Object>();
            String id = String.valueOf(hit.getSource().get("id"));
            String name = getHighLight(hit, "name");
            result.put("id", id);
            result.put("name", name);
            results.add(result);
        }
        ElasticSearchUtil.destory(client);
        return new RecordSet(0, results.size(), results.size(), results.toArray());
    }

    private String getHighLight(SearchHit hit, String field)
    {
        HighlightField hiField = hit.getHighlightFields().get(field);
        if (hiField != null)
        {
            Text[] contentFragments = hiField.getFragments();
            StringBuilder contentBuilder = new StringBuilder();
            for (Text text : contentFragments)
            {
                contentBuilder.append(text);
            }
            return contentBuilder.toString();
        }
        return null;
    }
}
