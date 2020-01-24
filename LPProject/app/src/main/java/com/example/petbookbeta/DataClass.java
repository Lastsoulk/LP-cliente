package com.example.petbookbeta;

import com.example.petbookbeta.clases.Animal;
import com.example.petbookbeta.clases.Doctor;
import com.example.petbookbeta.clases.Veterinaria;
import com.example.petbookbeta.clases.vacunas;

import java.util.ArrayList;
import java.util.List;

public class DataClass {
    public static final String ip_add = "192.168.56.1";
    public static String loggedUser = "";
    public static String loggedName = "";
    public static String logubi = "";
    public static String logedad = "";
    public static List<Animal> mascotas = new ArrayList<>();
    public static List<vacunas> vacunas = new ArrayList<>();
    public static List<Veterinaria> veterinarias = new ArrayList<>();
    public static List<Doctor> doctores = new ArrayList<>();
    public static List<Animal> tinder = new ArrayList<>();
}
