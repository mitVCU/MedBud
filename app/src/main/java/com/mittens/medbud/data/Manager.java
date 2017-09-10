package com.mittens.medbud.data;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Created by mit on 9/9/17.
 */

public class Manager {

    public static AmazonDynamoDBClient dynamoDBClient = null;
    public static DynamoDBMapper dynamoDBMapps = null;
    CognitoCachingCredentialsProvider credentialsProvider = null;

    public static CognitoCachingCredentialsProvider getcredentials(Context context) {
        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                "us-east-1:9ebba5b5-ced2-4be3-9381-87ecadefae55", // Identity pool ID
                Regions.US_EAST_1 // Region
        );


        return credentialsProvider;
    }

    public DynamoDBMapper initDynamoClient(CognitoCachingCredentialsProvider credentialsProvider) {
        if (dynamoDBClient == null) {
            dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);
            dynamoDBClient.setRegion(Region.getRegion(Regions.US_EAST_1));
            dynamoDBMapps = new DynamoDBMapper(dynamoDBClient);
        }
        return dynamoDBMapps;
    }


}

