package com.example.pawfect_1;

import java.util.List;

public class Dog_Search_Diseases {
    List<String> treatment;
    List<String> causes ;
    List<String> diseases;


    public Dog_Search_Diseases(List<String> treatment, List<String> causes, List<String> diseases) {
        this.diseases = diseases;
        this.causes = causes;
        this.treatment = treatment;
    }

    public List<String> getText1() {
        return diseases;
    }
    public List<String> getText2() {
        return causes;
    }
    public List<String> getText3() {
        return treatment;
    }
}