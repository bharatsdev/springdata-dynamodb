package com.poc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.poc.dynamo.model.EmpInfo;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.poc.dynamo")
public class DynamoDBConfig {

	private static Logger logger = LoggerFactory.getLogger(DynamoDBConfig.class);

	@Value("${aws.access.key}")
	String awsAccessKey;

	@Value("${aws.secret.key}")
	String awsSecretKey;

	@Value("${aws.dynamo.table}")
	String tableNameReplacement;

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		logger.info("[INFO] :  Dynamo DB endPOint : {}", client.listTables());

		DynamoDBMapper dbMapper = new DynamoDBMapper(client);

		CreateTableRequest createTableRequest = dbMapper.generateCreateTableRequest(EmpInfo.class);
		createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(1l, 1l));

		TableUtils.createTableIfNotExists(client, createTableRequest);

		return client;
	}

	@Bean
	public DynamoDBMapperConfig.TableNameOverride getTableNameOverride() {
		logger.info("Executed : {} : {}", "getTableNameOverride", tableNameReplacement);
		return DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableNameReplacement);
	}

	@Bean
	public DynamoDBMapperConfig dynamoDBMapperConfig(TableNameOverride tableNameOverride) {
		logger.info("Executed : {}", "getDynamoDBMapperConfig()");

		DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
		builder.setTableNameOverride(tableNameOverride);
		builder.setTypeConverterFactory(DynamoDBTypeConverterFactory.standard());
		return builder.build();
	}

}
