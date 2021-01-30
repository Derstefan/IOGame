package org.iogame.model.map;

import org.iogame.model.planet.Planet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class MapGen {
    private static final int height = 100;
    private static final int width = 200;
    private static final int maxPlanet = 15;
    private static final int minPlanet = 4;
    private static final int orbit = 5;
    private long numberPlanets;
 //   long seed_Game;
    private List<List<Double>> dis = new ArrayList<List<Double>>();
    private List<Planet> planetList = new ArrayList<>();

//    public MapGen(long seed_Game){
//    this.seed_Game = seed_Game;
//    }
//    public MapGen(){ }

    public List<Planet> generate(long seed_Game) {
        // Generation of seeds for Map
        Random seedGen = new Random(seed_Game);
        long seed_X = seedGen.nextLong();
        long seed_Y = seedGen.nextLong();
        long seed_backup = seedGen.nextLong();
        planetList = new ArrayList<>();

        // Map Values Construction (# of Planets, Coordinates of Planets)
        numberPlanets = new Random(seed_Game).nextInt(maxPlanet - minPlanet) + minPlanet;
        Random xGen = new Random(seed_X);
        Random yGen = new Random(seed_Y);
        for (int i = 0; i < numberPlanets; i++) {
            Planet p = new Planet(Math.round(xGen.nextDouble() * width * 100) * .01, Math.round(yGen.nextDouble() * height * 100) * .01);
            planetList.add(p);
        }

        distance(planetList);                                       //dis.forEach(System.out::println);

        for (List<Double> row : dis){                               //int i = 0; i < numberPlanets; i++) {
            for (double d : row){                                   //int j = 0; j < numberPlanets; j++) {
                if (d < orbit && d !=0){                            //dis.get(i).get(j) < orbit && dis.get(i).get(j) != 0) {
                    //System.out.println("Planets are too close. Regenerate!");
                    // seed_Game=seed_backup;
                    planetList = generate(seed_backup);
                    //System.out.println("Loop exitoso!");
                    return planetList;
                }
            }
        }
        return planetList;
    }
    //Distance Matrix Calculator
    public void distance(List<Planet> planetList) {
            dis = new ArrayList<List<Double>>();
            for (int j = 0; j < numberPlanets; j++) {
            Vector<Double> d = new Vector<>();
            for (int i = 0; i < numberPlanets; i++) {
                d.add(Math.sqrt(Math.pow(planetList.get(i).getX() - planetList.get(j).getX(), 2) + Math.pow(planetList.get(i).getY() - planetList.get(j).getY(), 2)));
            }
            dis.add(d);
        }
    }

    // Print functions
    public void printList(List<Planet> list) {
        System.out.println("The List of Planets reads:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("(" + list.get(i).getX() + ", " + list.get(i).getY() + ")");
        }
    }
//    public void seedsPrint() {
//        System.out.println(" ");
//        // System.out.println("Seeds: " + seed_Game + " "+ seedGen +" "+  seed_X +" "+ seed_Y);
//        System.out.println("");
////        seeds.forEach(System.out::println);
////        System.out.println(" ");
////        System.out.println("Number of planets:" + numberPlanets);
//    }


//    public static void main(String[] args) {
//
//        long seed_Game = new Random().nextLong();
//
//        MapGen mg = new MapGen();
//        mg.generate(seed_Game);
//
//        // Print List of Planets and Distance Matrix
//        System.out.println(" ");
//        System.out.println("# of Planets " + mg.numberPlanets);
//        System.out.println(" ");
//       // mg.printList(mg.planetList);
//        System.out.println("");
//        System.out.println("Distance: ");
//        mg.dis.forEach(System.out::println);
//        System.out.println("");
////        for (List<Double> vd: mg.dis) {
////            System.out.println(vd);
////        }
//    }
}
