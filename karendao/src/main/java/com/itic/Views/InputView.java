package com.itic.Views;

import java.io.BufferedReader;
import java.io.IOException;

import org.hibernate.SessionFactory;

import com.itic.DAO.DepartamentDAO;
import com.itic.DAO.EmpleatDAO;
import com.itic.DAO.EmpresaDAO;
import com.itic.DAO.TasquesDAO;
import com.itic.model.Departament;
import com.itic.model.Empleat;
import com.itic.model.Empresa;
import com.itic.model.Tasques;

public class InputView {

    public static void MostrarMenu(BufferedReader bf, SessionFactory sf){
        boolean continuar = true;

        while (continuar) {
            menuPrincipal();

            try {
                int opcio = Integer.parseInt(LecturaEntrada(bf));

                switch (opcio){
                    case 1:
                        menuCrear(bf, sf);
                        break;
                    case 2:
                        menuModificar(bf, sf);
                        break;
                    case 3:
                        menuLlistar(bf, sf);
                        break;
                    case 4:
                        menuEsborrar(bf, sf);
                        break;
                    case 5:
                        continuar = false; // sortir
                        System.out.println("Adéu!");
                        break;
                    default:
                        System.out.print("Opció no vàlida! Prem 'S' per tornar a veure el menú: ");
                        if (!LecturaEntrada(bf).equalsIgnoreCase("s")) {
                            continuar = false; // Si l'usuari no prem 'S', surt del bucle
                         }
                }
                
            } catch (NumberFormatException ex) {
                System.out.println("Entrada no vàlida. Si us plau, introdueix un número.");
            } catch (Exception ex) {
                System.err.println("Error general: " + ex.getMessage());
            }
        }
    }

    private static void menuPrincipal(){
        System.out.println("\nMAIN MENU");
        System.out.println("1. Crear");
        System.out.println("2. Modificar");
        System.out.println("3. Llistar");
        System.out.println("4. Esborrar");
        System.out.println("5. Sortir");
        System.out.println("Escull una opció: ");
        
    }

    private static void menuCrear(BufferedReader bf, SessionFactory sf) {
        try {
            boolean continuarCrear = true;
            EmpresaDAO empresaDAO = new EmpresaDAO(sf);
            DepartamentDAO departamentDAO = new DepartamentDAO(sf);
            EmpleatDAO empleatDAO = new EmpleatDAO(sf);
            TasquesDAO tasquesDAO = new TasquesDAO(sf);
    
            while (continuarCrear) {
                System.out.println("\nCREATE");
                System.out.println("1. Crear EMPRESA");
                System.out.println("2. Crear DEPARTAMENT");
                System.out.println("3. Crear EMPLEAT");
                System.out.println("4. Crear TASCA");
                System.out.println("5. Sortir");
                System.out.print("Escull una opció: ");
    
                int opcioCrear = Integer.parseInt(bf.readLine());
    
                switch (opcioCrear) {
                    case 1:
                        // Crear EMPRESA
                        System.out.print("Introduzca el nombre de la empresa: ");
                        String nombreEmpresa = bf.readLine();
    
                        // Verificar si el nombre está vacío
                        if (nombreEmpresa == null || nombreEmpresa.trim().isEmpty()) {
                            System.out.println("El nombre de la empresa no puede estar vacío.");
                        } else {
                            // Pedir la descripción de la empresa
                            System.out.print("Introduzca la descripción de la empresa: ");
                            String descripcioEmpresa = bf.readLine();

                            // Verificar si la descripción está vacía
                            if (descripcioEmpresa == null || descripcioEmpresa.trim().isEmpty()) {
                                System.out.println("La descripción de la empresa no puede estar vacía.");
                            } else {
                                 // Si el nombre y la descripción son válidos, crear la empresa
                                Empresa empresa = new Empresa();
                                empresa.setNom(nombreEmpresa);
                                empresa.setDescripcio(descripcioEmpresa);

                                // Guardar la empresa en la base de datos
                                empresaDAO.save(empresa);
                                System.out.println("EMPRESA creada correctamente.");
                            }
                        }
                        break;
                    case 2:
                        // Crear DEPARTAMENT
                        System.out.print("Introduzca el nombre del departamento: ");
                        String nomDepartament = bf.readLine();
    
                        Departament departament = new Departament();
                        departament.setNom(nomDepartament);
    
                        departamentDAO.save(departament);
                        System.out.println("DEPARTAMENT creat correctament");
                        break;
    
                    case 3:
                        // Crear EMPLEAT
                        System.out.print("Introduzca el DNI del empleado: ");
                        int dniEmpleat = Integer.parseInt(bf.readLine());
    
                        System.out.print("Introduzca el nombre del empleado: ");
                        String nomEmpleat = bf.readLine();
    
                        System.out.print("Introduzca el apellido del empleado: ");
                        String cognomEmpleat = bf.readLine();
    
                        System.out.print("Introduzca el id del departamento del empleado: ");
                        int idDepartament = Integer.parseInt(bf.readLine());
                        Departament departamentEmpleat = departamentDAO.find(idDepartament); // Busca el departamento por id
    
                        Empleat empleat = new Empleat(dniEmpleat, nomEmpleat, cognomEmpleat, departamentEmpleat);
    
                        empleatDAO.save(empleat);
                        System.out.println("EMPLEAT creat correctament");
                        break;
    
                    case 4:
                        // Crear TASCA
                        System.out.print("Introduzca la descripción de la tarea: ");
                        String descripcioTasca = bf.readLine();
    
                        System.out.print("Introduzca el id del empleado principal de la tarea: ");
                        int idEmpleatPrincipal = Integer.parseInt(bf.readLine());
                        Empleat empleatPrincipal = empleatDAO.find(idEmpleatPrincipal); // Busca el empleado por id
    
                        Tasques tasca = new Tasques();
                        tasca.setDescripcio(descripcioTasca);
                        tasca.setEmpleatPrincipal(empleatPrincipal);
    
                        tasquesDAO.save(tasca);
                        System.out.println("TASCA creada correctament");
                        break;
    
                    case 5:
                        continuarCrear = false;  // Salir del menú de crear
                        break;
    
                    default:
                        System.out.println("Opció no vàlida");
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Entrada no vàlida. Si us plau, introdueix un número.");
        } catch (IOException ex) {
            System.err.println("Error general: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    
    private static void menuModificar(BufferedReader bf, SessionFactory sf){
        try {
            boolean continuarModificar = true;
            EmpresaDAO empresaDAO = new EmpresaDAO(sf);
            DepartamentDAO departamentDAO = new DepartamentDAO(sf);
            EmpleatDAO empleatDAO = new EmpleatDAO(sf);
            TasquesDAO tasquesDAO = new TasquesDAO(sf);

            while (continuarModificar) {
                System.out.println("\nUPDATE");
                System.out.println("1. Modificar EMPRESA");
                System.out.println("2. Modificar DEPARTAMENT");
                System.out.println("3. Modificar EMPLEAT");
                System.out.println("4. Modificar TASCA");
                System.out.println("5. Sortir");
                System.out.print("Escull una opció: ");

                int opcioModificar = Integer.parseInt(bf.readLine());

                switch (opcioModificar) {
                    case 1:
                        Empresa empresa = new Empresa();
                        empresaDAO.update(empresa);  
                        System.out.println("EMPRESA modificada correctament");
                        break;
                    case 2:

                        Departament departament = new Departament();
                        departamentDAO.update(departament);
                        System.out.println("DEPARTAMENT modificat correctament");
                        break;
                    case 3:
                        
                        Empleat empleat = new Empleat();
                        empleatDAO.update(empleat);
                        System.out.println("EMPLEAT modificat correctament");
                        break;
                    case 4:
                        
                        Tasques tasca = new Tasques();
                        tasquesDAO.update(tasca);
                        System.out.println("TASCA modificada correctament");
                        break;
                    case 5:
                        continuarModificar = false;  // Salir del menú de modificar
                        break;
                    default:
                        System.out.println("Opció no vàlida");
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Entrada no vàlida. Si us plau, introdueix un número.");
        } catch (IOException ex) {
            System.err.println("Error general: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
       
    }

    private static void menuLlistar(BufferedReader bf, SessionFactory sf){
        try {
            boolean continuarLlistar = true;
            EmpresaDAO empresaDAO = new EmpresaDAO(sf);
            DepartamentDAO departamentDAO = new DepartamentDAO(sf);
            EmpleatDAO empleatDAO = new EmpleatDAO(sf);
            TasquesDAO tasquesDAO = new TasquesDAO(sf);

            while (continuarLlistar) {
                System.out.println("\nHQL");
                System.out.println("1. Llistar EMPRESA");
                System.out.println("2. Llistar DEPARTAMENT");
                System.out.println("3. Llistar EMPLEAT");
                System.out.println("4. Llistar TASCA");
                System.out.println("5. Sortir");
                System.out.print("Escull una opció: ");

                int opcioLlistar = Integer.parseInt(bf.readLine());

                switch (opcioLlistar) {
                    case 1:
                        empresaDAO.obtenirTotes();
                        break;
                    case 2:
                        departamentDAO.obtenirTots();
                        break;
                    case 3:
                        empleatDAO.obtenirTotes();
                        break;
                    case 4:
                        tasquesDAO.obtenirTotes();
                        break;
                    case 5:
                        continuarLlistar = false;  // Salir del menú de llistar
                        break;
                    default:
                        System.out.println("Opció no vàlida");
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Entrada no vàlida. Si us plau, introdueix un número.");
        } catch (IOException ex) {
            System.err.println("Error general: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        
    }

    private static void menuEsborrar(BufferedReader bf, SessionFactory sf){
        try {
            boolean continuarEsborrar = true;
            EmpresaDAO empresaDAO = new EmpresaDAO(sf);
            DepartamentDAO departamentDAO = new DepartamentDAO(sf);
            EmpleatDAO empleatDAO = new EmpleatDAO(sf);
            TasquesDAO tasquesDAO = new TasquesDAO(sf);

            while (continuarEsborrar) {
                System.out.println("\nDELETE");
                System.out.println("1. Esborrar EMPRESA");
                System.out.println("2. Esborrar DEPARTAMENT");
                System.out.println("3. Esborrar EMPLEAT");
                System.out.println("4. Esborrar TASCA");
                System.out.println("5. Sortir");
                System.out.print("Escull una opció: ");

                int opcioEsborrar = Integer.parseInt(bf.readLine());

                switch (opcioEsborrar) {
                    case 1:
                        System.out.print("Introdueix l'ID de l'EMPRESA a esborrar: ");
                        int idEmpresa = Integer.parseInt(bf.readLine());
                        empresaDAO.eliminarEmpresa(idEmpresa);
                        break;
                    case 2:
                        System.out.print("Introdueix l'ID del DEPARTAMENT a esborrar: ");
                        int idDepartament = Integer.parseInt(bf.readLine());
                        departamentDAO.eliminarDepartament(idDepartament);
                        break;
                    case 3:
                        System.out.print("Introdueix l'ID de l'EMPLEAT a esborrar: ");
                        int idEmpleat = Integer.parseInt(bf.readLine());
                        empleatDAO.eliminarEmpleat(idEmpleat);
                        break;
                    case 4:
                        System.out.print("Introdueix l'ID de la TASCA a esborrar: ");
                        int idTasca = Integer.parseInt(bf.readLine());
                        tasquesDAO.eliminarTasques(idTasca);
                        break;
                    case 5:
                        continuarEsborrar = false;  // Salir del menú de esborrar
                        break;
                    default:
                        System.out.println("Opció no vàlida");
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Entrada no vàlida. Si us plau, introdueix un número.");
        } catch (IOException ex) {
            System.err.println("Error general: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        
    }



    

    public static String LecturaEntrada(BufferedReader bf) {
         String st1 = null;
        try {
                st1 = bf.readLine();
        } catch (IOException e) {
            System.err.println("Error d'entrada: " + e.getMessage());
        } catch (Exception ex) {
            System.err.println("Error general: " + ex.getMessage());
        }
    
        return st1;
    }
}

