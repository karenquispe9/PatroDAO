package com.itic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.SessionFactory;

import com.itic.Views.InputView;
import com.itic.HibernateUtil;

public class Main {
    public static void main(String[] args) {

        SessionFactory s1 = HibernateUtil.getSessionFactory();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("=============================");
            System.out.println("Gestió de work_db");
            System.out.println("=============================");
            InputView.MostrarMenu(bf,s1);

        } catch (IOException ioe) {
            System.err.println("Error d'entrada");
        }

    }

}   