package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Task {

    private ArrayList<Double> probability;
    private ArrayList<Double> amount;
    private double ksi;
    private int time;
    private int interval;
    private double false_percent;
    private double loss;
    private double false_inf;
    private double true_inf;
    private double all_inf;

    public ArrayList<Double> getProbability() {
        return probability;
    }

    public void setProbability(ArrayList<Double> probability) {
        this.probability = probability;
    }

    public ArrayList<Double> getAmount() {
        return amount;
    }

    public void setAmount(ArrayList<Double> amount) {
        this.amount = amount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public double getFalse_percent() {
        return false_percent;
    }

    public void setFalse_percent(double false_percent) {
        this.false_percent = false_percent;
    }

    public double getKsi() {
        return ksi;
    }

    public void setKsi(double ksi) {
        this.ksi = ksi;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }

    public double getFalse_inf() {
        return false_inf;
    }

    public void setFalse_inf(double false_inf) {
        this.false_inf = false_inf;
    }

    public double getTrue_inf() {
        return true_inf;
    }

    public void setTrue_inf(double true_inf) {
        this.true_inf = true_inf;
    }

    public double getAll_inf() {
        return all_inf;
    }

    public void setAll_inf(double all_inf) {
        this.all_inf = all_inf;
    }

    public Task(int time, int interval, double loss) {

        this.time = time;
        this.interval = interval;
        this.loss = loss;
        this.ksi = 0;
        this.false_percent = 0;
        this.false_inf = 0;
        this.true_inf = 0;
        this.all_inf = 0;

        probability = new ArrayList<>();

        probability.add(0.4);
        probability.add(0.2);
        probability.add(0.1);
        probability.add(0.2);
        probability.add(0.1);

        amount = new ArrayList<>();

        amount.add(200.0);
        amount.add(500.0);
        amount.add(1.0);
        amount.add(1.5);
        amount.add(2.0);
    }

    public double GetKsi() {
        Random r = new Random();

        ksi = r.nextDouble();

        return ksi;
    }

    public double GetAmount(){

        double L = 0;

        for(int i = 0; i < probability.size(); i++){
        L += probability.get(i);
            if(GetKsi() <= L){
                return amount.get(i);
            }
        }
        return 0.0;
    }

    private boolean GetLoss(){
        Random random = new Random();

        return random.nextDouble() <= loss;
    }

    public void GetAllInfo(){

        double f = 0;
        double t = 0;

        for(int i = 0; i <= time; i += interval){
            f = 0;
            if(GetLoss()){
                f = GetAmount();
                false_inf += f;
                System.out.println("Объём ложного сообщения = " + f);

            } else{

            t = GetAmount();
            true_inf += t;
            System.out.println("Получено: " + t);}

            all_inf += f + t;
        }

        System.out.println("\n Полученный объём = " + all_inf + "; без ошибок = " + true_inf + "; ложно = " + false_inf + "\n");
    }

    public void GetPercentFalse(){

        false_percent = (false_inf / all_inf) * 100;

        System.out.println("\n Процент ложной информации = " + false_percent + " %");
    }
}
