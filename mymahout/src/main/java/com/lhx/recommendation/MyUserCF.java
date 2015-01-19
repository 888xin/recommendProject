package com.lhx.recommendation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by lhx on 15-1-19 上午9:52
 *
 * @project recommendProject
 * @package com.lhx.recommendation
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class MyUserCF {
    final static int NEIGHBORHOOD_NUM = 2 ;
    final static int RECOMMENDER_NUM = 3 ;

    public static void main(String[] args) throws IOException, TasteException {
        String file = "C:\\Users\\Lifeix\\Documents\\IdeaProjects\\recommendProject\\mymahout\\data\\item.csv" ;
        DataModel model = new FileDataModel(new File(file));
        UserSimilarity user = new EuclideanDistanceSimilarity(model);
        NearestNUserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
        Recommender r = new GenericUserBasedRecommender(model, neighborhood, user) ;
        LongPrimitiveIterator iter = model.getUserIDs();
        while (iter.hasNext()){
            long uid = iter.nextLong();
            List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
            System.out.printf("uid:%s", uid);
            for (RecommendedItem recommendedItem : list) {
                System.out.printf("(%s,%f)", recommendedItem.getItemID(), recommendedItem.getValue());
            }
            System.out.println();
        }
    }
}
