package com.lhx.recommendation;

import org.apache.mahout.clustering.kmeans.Cluster;
import org.apache.mahout.clustering.kmeans.KMeansClusterer;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.math.Vector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhx on 15-1-19 上午10:12
 *
 * @project recommendProject
 * @package com.lhx.recommendation
 * @Description
    1. Init Point center表示，kmeans算法初始时的设置的3个中心点
    2. Cluster center表示，聚类后找到3个中心点
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class MyKmeans {

    public static void main(String[] args) throws IOException {
        List<Vector> sampleData = MathUtil.readFileToVector("C:\\Users\\Lifeix\\Documents\\IdeaProjects\\recommendProject\\mymahout\\data\\randomData.csv");

        int k = 3 ;
        double threshold = 0.01 ;
        List<Vector> randomPoints = MathUtil.chooseRandomPoints(sampleData,k);
        for (Vector randomPoint : randomPoints) {
            System.out.println("Init Point center: " + randomPoint);
        }

        List<Cluster> clusters = new ArrayList<Cluster>();
        for (int i = 0; i < k; i++) {
            clusters.add(new Cluster(randomPoints.get(i), i, new EuclideanDistanceMeasure()));
        }

        List<List<Cluster>> finalClusters = KMeansClusterer.clusterPoints(sampleData, clusters, new EuclideanDistanceMeasure(), k, threshold);
        for (Cluster cluster : finalClusters.get(finalClusters.size() - 1)) {
            System.out.println("Cluster id: " + cluster.getId() + " center: " + cluster.getCenter().asFormatString());
        }
    }
}
