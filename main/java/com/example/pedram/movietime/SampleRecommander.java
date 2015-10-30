package com.example.pedram.movietime;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


public class SampleRecommander {

	public static void main(String[] args) throws IOException, TasteException {
		// TODO Auto-generated method stub

		DataModel model = new FileDataModel(new File("data/dataset.csv"));
		
//		UserSimilarity similarity = new LogLikelihoodSimilarity(model);
//		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserSimilarity similarity = new TanimotoCoefficientSimilarity(model);
		
//		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0, similarity, model,1);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(10202474253267104l, 5);
		for (RecommendedItem recommendation : recommendations) {
		  System.out.println(recommendation);
		}
	}

}
