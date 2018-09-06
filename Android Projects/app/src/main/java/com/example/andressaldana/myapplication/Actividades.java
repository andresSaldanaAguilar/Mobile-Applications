package com.example.andressaldana.myapplication;

public class Actividades {
    public String paridad(int num){
        if((num%2) == 0){
            return "El número "+num+" es par";
        }
        else{
            return "El número "+num+" es impar";
        }
    }
    public String Maravilloso(int num){
        String cadenaValores = "";

        cadenaValores = num + " ";
        if(num>=1)
            cadenaValores = "El numero " + cadenaValores + "Es maravilloso.";
        else
            cadenaValores = "El numero " + cadenaValores + "No es maravilloso.";
        while(num>1){
            if(num%2==0){//PAR
                num = num/2;
                cadenaValores = cadenaValores + "\n" + num;
            }
            else{//IMPAR
                num = num*3+1;
                cadenaValores = cadenaValores + "\n" + num;
            }
        }
        return cadenaValores;
    }

    public String Palindrome(String cadenaAnalizar){
        String cadenaAnalizada="" + cadenaAnalizar;
        String cadenaInvertida = "";
        String cadenaSinEspacios = "";

        for(int i=0; i<cadenaAnalizar.length();i++){
            if(cadenaAnalizar.charAt(i)!= 32){
                cadenaSinEspacios = cadenaSinEspacios + cadenaAnalizar.charAt(i);
            }
        }
        for(int i=cadenaSinEspacios.length()-1; i>=0; i--){
            cadenaInvertida = cadenaInvertida + cadenaSinEspacios.charAt(i);
        }

        if(cadenaSinEspacios.equalsIgnoreCase(cadenaInvertida)){
            cadenaAnalizada = cadenaAnalizada + " = " + cadenaInvertida + " ,Es palindroma.";
        }
        else{
            cadenaAnalizada = cadenaAnalizada + " != " + cadenaInvertida + " ,No es palindroma";
        }

        return cadenaAnalizada;
    }

    public String Fibonacci(int numero) {
        boolean esfibonacci = false;
        int FibonacciUno = 0;
        int FibonacciDos = 1;
        String cadenaValores = "";

        System.out.print(FibonacciUno + " ");
        do{
            cadenaValores += " "+FibonacciDos;
            FibonacciDos = FibonacciUno + FibonacciDos;
            FibonacciUno = FibonacciDos - FibonacciUno;
            if(numero == FibonacciDos){
                esfibonacci = true;
            }
        }while(FibonacciDos <= numero);

        if(esfibonacci){
            cadenaValores += "... es fibonacci";
            System.out.println(cadenaValores+"... es fibonacci");
        }else{
            cadenaValores += "... no es fibonacci";
            System.out.println(cadenaValores+"...no es fibonacci!");
        }
        return cadenaValores;
    }
}
