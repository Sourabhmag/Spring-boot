package com.bridgelabz.NoteService.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/******************************************************************************
 * Compilation: javac -d bin ElasticsearchConfig.java Execution:
 * 
 * 
 * Purpose: This Class is used to provide Configuration for elastic search
 *
 * @author Sourabh Magdum
 * @version 1.0
 * @since 26-11-2019
 *
 ******************************************************************************/
@Configuration
public class ElasticsearchConfig {
//
//	@Value("${elasticsearch.host}")
//	private String elasticsearchHost;
//
//	@Value("${elasticsearch.port}")
//	private Integer elasticsearchPort;
//
//	@Value("http")
//	private String elasticsearchScheme;
//
//	@Bean(destroyMethod = "close")
//	public RestHighLevelClient client() {
//		System.out.println(elasticsearchHost);
//		System.out.println(elasticsearchPort);
//		System.out.println(elasticsearchScheme);
//
//		RestHighLevelClient client = new RestHighLevelClient(
//				RestClient.builder(new HttpHost(elasticsearchHost, elasticsearchPort, elasticsearchScheme)));
//
//		return client;
//	}
}