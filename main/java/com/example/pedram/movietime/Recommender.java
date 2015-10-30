package com.example.pedram.movietime;

import android.util.Log;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nitin1992 on 03-05-2015.
 */
public class Recommender {


    public List<Long> recomm () throws TasteException, IOException {

        File mediaDir = new File("/sdcard/download/media");
        if (!mediaDir.exists()){
            mediaDir.mkdir();
        }

        File resolveMeSDCard = new File("/sdcard/download/data.csv");
        resolveMeSDCard.setReadable(true);
        //Read text from file
//        StringBuilder text = new StringBuilder();
//
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(resolveMeSDCard));
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                text.append(line);
//                text.append('\n');
//            }
//            br.close();
//        }
//        catch (IOException e) {
//            //You'll need to add proper error handling here
//        }
//
//        Log.d("look at this",text.toString());
        List<Long> list = new ArrayList<Long>();
        DataModel model = new FileDataModel(resolveMeSDCard);

//		UserSimilarity similarity = new LogLikelihoodSimilarity(model);
//		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserSimilarity similarity = new TanimotoCoefficientSimilarity(model);

//		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0, similarity, model,1);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        List<RecommendedItem> recommendations = recommender.recommend(10202474253267104l, 5);
        for (RecommendedItem recommendation : recommendations) {
            list.add(recommendation.getItemID());
        }

        return list;
    }
}
