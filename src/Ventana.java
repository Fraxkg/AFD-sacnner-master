import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class MainPanelTabla extends JPanel{
	Image fondo=new ImageIcon("img/i.jpg").getImage();
public MainPanelTabla(){
	
}
public void paintComponent(Graphics g){
	g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
}
}
class MainPanel extends JPanel{
	Image fondo=new ImageIcon("img/fondoDefault.jpg").getImage();
public MainPanel(){
	
}
public void paintComponent(Graphics g){
	g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
}
}

public class Ventana extends JFrame {
	JTable tablaLexica = new JTable(new DefaultTableModel(new Object[]{"No.", "Línea","TOKEN","Tipo","Código"}, 0));
	JTable tablaId = new JTable(new DefaultTableModel(new Object[]{"Identificadores", "Valor","Linea"}, 0));
	JTable tablaConst = new JTable(new DefaultTableModel(new Object[]{"Constantes", "Valor","Linea"}, 0));
	  
	
	JTable table = new JTable();
	String[] tokenUsado= new String[99];
	int[] tokenValorUsado= new int[99];
	int[] espacioTablaOcupado= new int[99];
	String[] lineas= new String[99];
	int lineaEntexto=0;
	boolean fondo=false;
	boolean flagError=false;
	JPanel app=new MainPanel();
	JPanel PanelTablaId=new MainPanelTabla();
	JPanel PanelTablaConst=new MainPanelTabla();
	JPanel PanelTablaLex=new MainPanelTabla();
	JLabel resultado=new JLabel();
	String[] cadenaArray;
	String[] elementArray = new String[99];
	String[][]datosLex=new String[5][100];
	String cadena;
	String campo;
	String lex="";
	int[] espacioTablaOcupadoId= new int[99];
	int contadoridentiId=0;
	int contadoridenti=0;
	char car=' ';
	boolean tablaCreada=false;
	boolean flagExpo=false;
	boolean flagCorreo=false;
	int auxEstado;
	int carMatriz;
	int estado=0;
	int cont=0;
	int tam=0;
	int vueltas=0;
	int elemento=0;
	int total=0;
	int No=1;
	int linea=1;
	int auxlinea=1;
	int id=100;
	int constante=200;
	int cicloLinea=0;
	boolean flagAId=false;
	boolean flagAConst=false;
	
	JScrollPane JSId=new JScrollPane(tablaId);
	JScrollPane JSConst=new JScrollPane(tablaConst);
	JScrollPane JSLex=new JScrollPane(tablaLexica);
	/*
	 letra=0
	 digito=1
	 / = 2
	 * 3
	 - 4
	 _ 5
	 . 6
	 @ 7
	 E 8
	 +/- 9
	 cualquiera 10
	 */
	
	int [][] tabla ={{ 1, 7, 3,19,19,19,19,19,19, 8,20},
					 { 1, 1,19,19,19, 2,18,14,19,19,20},
					 { 1, 1,19,19,19,19,19,19,19,19,20},
					 {19,19,19, 4,19,19,19,19,19,19,20},
					 { 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 20},
					 {19,19, 6,19,19,19,19,19,19,19,20},
					 {19,19,19,19,19,19,19,19,19,19,20},
					 {19, 7,19,19,19,19, 9,19,11,19,20},
					 {19, 7,19,19,19,19,19,19,19,19,20},
					 {19,10,19,19,19,19,19,19,19,19,20},
					 {19,10,19,19,19,19,19,19,11,19,20},
					 {19,12,19,19,19,19,19,19,19,13,20},
					 {19,12,19,19,19,19,19,19,19,19,20},
					 {19,12,19,19,19,19,19,19,19,19,20},
					 {15,19,19,19,19,19,19,19,19,19,20},
					 {15,19,19,19,19,19,16,19,19,19,20},
					 {17,19,19,19,19,19,19,19,19,19,20},
					 {17,19,19,19,19,19,19,19,19,19,20},
					 { 1, 1,19,19,19,19,19,19,19,19,20},
					 {19,19,19,19,19,19,19,19,19,19,20},
					 {19,19,19,19,19,19,19,19,19,19,19},
					 {20,20,20,20,20,20,20,20,20,20,20}};
	public Ventana() {

	////Visual
		JFrame frame = new JFrame();
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.setVisible(true);
	    
		
		frame.add(app);
		frame.setTitle("Escaer Jose Morales, Francisco Sánchez y Omar Verdugo");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocation(0,0);
        frame.setVisible(true);
		frame.setSize(1400,800);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setBackground(Color.BLUE);
		
		frame.repaint();
		app.setVisible(true);
		app.setLayout(null);
		app.setBackground(Color.GRAY);
		app.setBounds(0, 0, 1400, 800);
	
		
		
		JLabel instruccion= new JLabel("Inserta la cadena a validar");
		instruccion.setBounds(100, 5, 300, 131);
		font(instruccion,"Arial",20);
		instruccion.setForeground(Color.white);
		app.add(instruccion);
		
		resultado.setBounds(20, 400, 450, 280);
		resultado.setForeground(Color.WHITE);
		font(resultado,"Arial",14);
		
		JTextArea campoCadena=new JTextArea();
		campoCadena.setBounds(20, 100, 450, 200);
		app.add(campoCadena);
		app.add(resultado);
		
		JButton enviar=new JButton("Identificar");
		enviar.setBounds(20, 350, 100, 40);
		enviar.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//resetear cadena
				
				DefaultTableModel model1 = (DefaultTableModel) tablaLexica.getModel();
				model1.setRowCount(0);
				DefaultTableModel model2 = (DefaultTableModel) tablaId.getModel();
				model2.setRowCount(0);
				DefaultTableModel model3 = (DefaultTableModel) tablaConst.getModel();
				model3.setRowCount(0);
				
				//mostar cadena
				
				cadena = campoCadena.getText()+" ~";
				
				
				cadena=cadena.replaceAll("[\n\r]","  ");
				
				cadenaArray = cadena.split("");
				
				
				
				//
				if(campoCadena.getText().isEmpty()==true) {
					resultado.setText("Campo Vacío");
				
				}else {
					
					Scanner();
					Validar();
					
					tablaCreada=true;
					MaestroLimpio();
					showTable();
					
				}
				
			}
			public void showTable() {
				JSId.removeAll();
				JSLex.removeAll();
				JSConst.removeAll();
				frame.remove(JSId);
				frame.remove(JSLex);
				frame.remove(JSConst);
				 JSId=new JScrollPane(tablaId);
				
				JSId.setBounds(500,0,300,300);
				frame.add(JSId);
				JSId.setVisible(true);
				JSId.repaint();

				JSConst=new JScrollPane(tablaConst);
				
				JSConst.setBounds(500,300,300,300);
				frame.add(JSConst);
				JSConst.setVisible(true);
				JSConst.repaint();
					
				JSLex=new JScrollPane(tablaLexica);
				
				JSLex.setBounds(800,0,400,600);
				frame.add(JSLex);
				JSLex.setVisible(true);
				JSLex.repaint();
				tablaLexica = new JTable(new DefaultTableModel(new Object[]{"No.", "Línea","TOKEN","Tipo","Código"}, 0));
				tablaId = new JTable(new DefaultTableModel(new Object[]{"Identificadores", "Valor","Linea"}, 0));
				tablaConst = new JTable(new DefaultTableModel(new Object[]{"Constantes", "Valor","Linea"}, 0));
			}
			
			private void MaestroLimpio() {
				// TODO Auto-generated method stub
				datosLex=new String[5][100];
				
				
				
				contadoridenti=0;
				 car=' ';
				
				flagExpo=false;
				flagCorreo=false;
				auxEstado=0;
				 campo="";
				 estado=0;
				 cont=0;
				 tam=0;
				vueltas=0;
				 elemento=0;
				total=0;
				 No=1;
				 lex="";
				 linea=1;
				id=100;
				 constante=200;
				cicloLinea=0;
				
				id=100;
				constante=200;
				No=1;
				contadoridenti=0;
				contadoridentiId=0;
				linea=1;
				vueltas=0;
				tokenUsado= new String[99];
				tokenValorUsado= new int[99];
				espacioTablaOcupado= new int[99];
				espacioTablaOcupadoId= new int[99];
				lineas= new String[99];
				lineaEntexto=0;
				elementArray= new String[99];
				flagError=false;
				
				flagAId=false;
				flagAConst=false;
				elementArray = new String[99];
				datosLex=new String[5][100];
				
			}
			
		});
		app.add(enviar);
		
		JButton MostrarTablas=new JButton("Tablas");
		MostrarTablas.setBounds(220, 350, 100, 40);
		
		MostrarTablas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tablaCreada==true) {

					JScrollPane JSId=new JScrollPane(tablaId);
					
					JSId.setBounds(500,0,300,300);
					frame.add(JSId);
					JSId.setVisible(true);
					JSId.repaint();

					JScrollPane JSConst=new JScrollPane(tablaConst);
					
					JSConst.setBounds(500,300,300,300);
					frame.add(JSConst);
					JSConst.setVisible(true);
					JSConst.repaint();
						
					JScrollPane JSLex=new JScrollPane(tablaLexica);
					
					JSLex.setBounds(800,0,400,600);
					frame.add(JSLex);
					JSLex.setVisible(true);
					JSLex.repaint();
					tablaLexica = new JTable(new DefaultTableModel(new Object[]{"No.", "Línea","TOKEN","Tipo","Código"}, 0));
					tablaId = new JTable(new DefaultTableModel(new Object[]{"Identificadores", "Valor","Linea"}, 0));
					tablaConst = new JTable(new DefaultTableModel(new Object[]{"Constantes", "Valor","Linea"}, 0));
					
				}
				else {
					resultado.setText("No existen tablas");
				}
				}
			
		});
		app.repaint();
		
	
		JButton limpiar=new JButton("Limpiar");
		limpiar.setBounds(350, 350, 100, 40);
		app.add(limpiar);
		limpiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				campoCadena.setText("");
				datosLex=new String[5][100];
				
				
				
				contadoridenti=0;
				 car=' ';
				tablaCreada=false;
				flagExpo=false;
				flagCorreo=false;
				auxEstado=0;
				 campo="";
				 estado=0;
				 cont=0;
				 tam=0;
				vueltas=0;
				 elemento=0;
				total=0;
				 No=1;
				 lex="";
				 linea=1;
				id=100;
				 constante=200;
				cicloLinea=0;
				resultado.setText("");
				id=100;
				constante=200;
				No=1;
				contadoridenti=0;
				contadoridentiId=0;
				linea=1;
				vueltas=0;
				tokenUsado= new String[99];
				tokenValorUsado= new int[99];
				espacioTablaOcupado= new int[99];
				espacioTablaOcupadoId= new int[99];
				lineas= new String[99];
				lineaEntexto=0;
				elementArray= new String[99];
				flagError=false;
				flagAId=false;
				flagAConst=false;
				
				elementArray = new String[99];
				datosLex=new String[5][100];
				DefaultTableModel model1 = (DefaultTableModel) tablaLexica.getModel();
				model1.setRowCount(0);
				DefaultTableModel model2 = (DefaultTableModel) tablaId.getModel();
				model2.setRowCount(0);
				DefaultTableModel model3 = (DefaultTableModel) tablaConst.getModel();
				model3.setRowCount(0);
				tablaLexica = new JTable(new DefaultTableModel(new Object[]{"No.", "Línea","TOKEN","Tipo","Código"}, 0));
				tablaId = new JTable(new DefaultTableModel(new Object[]{"Identificadores", "Valor","Linea"}, 0));
				tablaConst = new JTable(new DefaultTableModel(new Object[]{"Constantes", "Valor","Linea"}, 0));
				
			}
			
		});
		app.repaint();
		
	}
	public void fontText(JTextField text,String tipo,int size){
		
		Font font=new Font(tipo,Font.BOLD,size);
		text.setFont(font);
	
	}
	
	public void font(JLabel label,String tipo,int size){
		
		Font font=new Font(tipo,Font.BOLD,size);
		label.setFont(font);
	
	}
	
	///visual fin
/*Modo águila
		JLabel background;
		ImageIcon img = new ImageIcon("img/americanPower.jpg");
		background= new JLabel("",img,JLabel.CENTER);
		background.setBounds(0,0,1200,600);
		
		JButton modoAguila=new JButton("Modo: Águila");
		modoAguila.setBounds(1000, 500, 150, 50);
		modoAguila.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				if(fondo==false) {
					app.add(background);
					app.repaint();
					fondo=true;
				}else {
					app.remove(background);
					app.repaint();
					fondo=false;
				}
				
			}
			
		});
		app.add(modoAguila);
fin modo aguila*/
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ventana v= new Ventana();
		
		}

	
	
public void Scanner() {
	
	tam=cadenaArray.length;
	char aux=' ';
	
	do {
		
		aux=car;
		car=cadenaArray[cont].charAt(0);
		
		if(car==' ') {
			
			if(aux==' ') {
				lex="";
				if(cadenaArray[cont+1].charAt(0)!=' ') {
				linea++;
				}
			}else if(aux!=' '){
				elementArray[elemento] = lex;
				elemento++;
				lineaEntexto++;
				lex="";
				
				
				
			}
		}else {
			
			lex=lex+car;
		}
		lineas[lineaEntexto]=Integer.toString(linea);
		System.out.println("linea"+lineas[lineaEntexto]);
		
		cont++;

	}while(car!='~');
	cont=0;
	elemento=0;
	

}
public void Validar() {
	
	/*
	 
Tokens Valor inicial
Delimitadores 50
Operadores 70
Identificadores (i) 100
Constantes (c) 200
Reglas 300
( 50 + 70
) 51 - 71
; 52 * 72
	 / 73 */
	
	
	
	
	do {
		if(cicloLinea==vueltas){
			
			linea=Integer.parseInt(lineas[cicloLinea]);
			
			cicloLinea++;
	}
			
			
		if(elementArray[vueltas].equals("+")) {
			
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,"+","7","70");
			
		}else if(elementArray[vueltas].equals("–") || (elementArray[vueltas].equals("-")) || (elementArray[vueltas].equals("—"))) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,"-","7","71");
			
		}else if(elementArray[vueltas].equals("*")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,"*","7","72");
			
		}else if(elementArray[vueltas].equals("/")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,"/","7","73");
			
		}else if(elementArray[vueltas].equals("(")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,"(","5","50");
			
		}else if(elementArray[vueltas].equals(")")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,")","5","51");
			
		}else if(elementArray[vueltas].equals(";")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,";","5","52");
			
		}else {
			cadena = elementArray[vueltas];
			cadenaArray = cadena.split("");
			calcular();
		}
		
		No++;
		vueltas++;
		
		
	}while(elementArray[vueltas]!=null);
	vueltas=0;
	int No=1;
	int linea=1;
	
}
//calcular cadena
	public void calcular() {
		
		tam=cadenaArray.length;
		do {
			auxEstado=estado;
			cadenaArray[cont]=cadenaArray[cont].toLowerCase();
			car=cadenaArray[cont].charAt(0);
			/*
			 letra=0
			 digito=1
			 / = 2
			 * 3
			 - 4
			 _ 5
			 . 6
			 @ 7
			 E 8
			 +/- 9
			 cualquiera 10
			 */
			if(flagExpo==false&&(car=='a' ||car=='b'||car=='c'||car=='d'||car=='e'||car=='f'||car=='g'||car=='h'||car=='i'||car=='j'||car=='k'||car=='m'||car=='n'||car=='l'||car=='o'||car=='p'||car=='q'||car=='r'||car=='s'||car=='u' ||car=='v'||car=='t'||car=='w'||car=='x'||car=='y'||car=='z')) {
				
			carMatriz=0;
			
			}else if(car=='1' || car=='2' || car=='3' || car=='4' || car=='5' || car=='6' || car=='7' || car=='8' || car=='9' || car=='0') {
				carMatriz=1;
			}else if(car=='/') {
				carMatriz=2;
			}else if(car=='*') {
				carMatriz=3;
			}else if(car=='_') {
				carMatriz=5;
			}else if(car=='.') {
				flagCorreo=true;
				carMatriz=6;
			}else if(car=='@') {
				carMatriz=7;
			}else if(car=='e') {
				carMatriz=8;
			}else if(car=='+'||car=='-' || car=='-') {
				carMatriz=9;
			}else{
				carMatriz=10;
			}
			if(estado==7) {
				flagExpo=true;
			}
			estado= tabla[auxEstado][carMatriz];
			
			cont++;
		}while(cont<tam);
		
		if (estado==1 && flagCorreo==false) {
			
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String LineaId=String.valueOf(linea);
			int auxId=0;
			int auxEspacio=0;
			String identificador=String.valueOf(id);
			boolean existe=false;
			
			//System.out.println(tokenUsado[0]);
			//System.out.println(elementArray[0]);
			for(int i=0;i<tokenUsado.length;i++) {
				if(elementArray[vueltas].equals(tokenUsado[i]) ) {
					//
					
					existe=true;
					break;
				}else {
					existe=false;
					
				}
					
			}
			if(existe==true ) {
				
				for(int i=0;i<tokenUsado.length;i++) {
				if(elementArray[vueltas].equals(tokenUsado[i])) {
					
					auxId=tokenValorUsado[i];
					
					break;
				}
				}
				for(int i=0;i<espacioTablaOcupadoId.length;i++) {
					if(elementArray[vueltas].equals(tokenUsado[i])) {
						
						if(flagAId==false) {
						auxEspacio=espacioTablaOcupadoId[i];
						System.out.println("espacio adquirido"+auxEspacio);
						LineaId =(String)tablaId.getValueAt(auxEspacio-1, 2)+", "+linea;
						DefaultTableModel model1 = (DefaultTableModel)tablaId.getModel();

						model1.setValueAt(LineaId, auxEspacio-1, 2);
						}else {
							auxEspacio=espacioTablaOcupadoId[i-1];
							System.out.println("espacio adquirido"+auxEspacio);
							LineaId =(String)tablaId.getValueAt(auxEspacio, 2)+", "+linea;
							DefaultTableModel model1 = (DefaultTableModel)tablaId.getModel();

							model1.setValueAt(LineaId, auxEspacio, 2);
							flagAId=true;
						}
						
						
						
						break;
					}
					
					}
				flagAConst=true;
				//Linea=(String) tablaId.getValueAt(auxEspacio, 3);
				//System.out.println("lineas"+ tablaId.getValueAt(auxEspacio-1, 2));
				
				identificador=String.valueOf(auxId);
				Salida(Numero,Linea,elementArray[vueltas],"1",identificador);
				if(flagError==false) {
					resultado.setText("1:100 Sin errores");
				}
			}else {
				
				contadoridenti++;
				
				
				id++;
				
				identificador=String.valueOf(id);
				espacioTablaOcupadoId[contadoridenti]=contadoridenti;
				tokenUsado[contadoridenti]=elementArray[vueltas];
				tokenValorUsado[contadoridenti]=id;
				
						
				SalidaId(elementArray[vueltas],identificador,Linea);
				
				Salida(Numero,Linea,elementArray[vueltas],"1",identificador);
				
			}
			if(flagError==false) {
				resultado.setText("1:100 Sin errores");
			}

		}else if(estado==7){
		
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String consta=String.valueOf(constante);
			String LineaConst=String.valueOf(linea);
			int auxConst=0;
			int auxEspacio=0;
			boolean existe=false;

			for(int i=0;i<tokenUsado.length;i++) {
				if(elementArray[vueltas].equals(tokenUsado[i]) ) {
					//
					
					existe=true;
					break;
				}else {
					existe=false;
					
				}
					
			}
			if(existe==true ) {
				
				for(int i=0;i<tokenUsado.length;i++) {
				if(elementArray[vueltas].equals(tokenUsado[i])) {
					
					auxConst=tokenValorUsado[i];
					break;
				}
				}
				for(int i=0;i<espacioTablaOcupado.length+1;i++) {
					if(elementArray[vueltas].equals(tokenUsado[i])) {
						
						if(flagAConst==false) {
							auxEspacio=espacioTablaOcupado[i];
							System.out.println("espacio adquirido"+auxEspacio);
							LineaConst =(String)tablaConst.getValueAt(auxEspacio-1, 2)+", "+linea;
							DefaultTableModel model2 = (DefaultTableModel)tablaConst.getModel();

							model2.setValueAt(LineaConst, auxEspacio-1, 2);
							}else {
								auxEspacio=espacioTablaOcupado[i-1];
								System.out.println("espacio adquirido"+auxEspacio);
								LineaConst =(String)tablaConst.getValueAt(auxEspacio, 2)+", "+linea;
								DefaultTableModel model2 = (DefaultTableModel)tablaConst.getModel();

								model2.setValueAt(LineaConst, auxEspacio, 2);
								flagAConst=true;
							}
							
							
						
						
						
						break;
					}
					
					}
				flagAId=true;
				consta=String.valueOf(auxConst);
				Salida(Numero,Linea,elementArray[vueltas],"2",consta);
				
				
			}else {
				
				contadoridenti++;
			
				constante=constante+1;
				
				consta=String.valueOf(constante);
				espacioTablaOcupado[contadoridenti]=contadoridenti;
				tokenUsado[contadoridenti]=	elementArray[vueltas];
				tokenValorUsado[contadoridenti]=constante;
				
				SalidaConst(elementArray[vueltas],consta,Linea);
				Salida(Numero,Linea,elementArray[vueltas],"2",consta);
			}if(flagError==false) {
				resultado.setText("1:100 Sin errores");
			}
			
			
		}else if(estado==10){

				String Numero=String.valueOf(No);
				String Linea=String.valueOf(linea);
				String consta=String.valueOf(constante);
				String LineaConst=String.valueOf(linea);
				int auxConst=0;
				int auxEspacio=0;
				boolean existe=false;

				for(int i=0;i<tokenUsado.length;i++) {
					if(elementArray[vueltas].equals(tokenUsado[i]) ) {
						//
						
						existe=true;
						break;
					}else {
						existe=false;
						
					}
						
				}
				if(existe==true ) {
					
					for(int i=0;i<tokenUsado.length;i++) {
					if(elementArray[vueltas].equals(tokenUsado[i])) {
						
						auxConst=tokenValorUsado[i];
						break;
					}
					}
					for(int i=0;i<espacioTablaOcupado.length+1;i++) {
						if(elementArray[vueltas].equals(tokenUsado[i])) {
							
							if(flagAConst==false) {
								auxEspacio=espacioTablaOcupado[i];
								System.out.println("espacio adquirido"+auxEspacio);
								LineaConst =(String)tablaConst.getValueAt(auxEspacio-1, 2)+", "+linea;
								DefaultTableModel model2 = (DefaultTableModel)tablaConst.getModel();

								model2.setValueAt(LineaConst, auxEspacio-1, 2);
								}else {
									auxEspacio=espacioTablaOcupado[i];
									System.out.println("espacio adquirido"+auxEspacio);
									LineaConst =(String)tablaConst.getValueAt(auxEspacio-i, 2)+", "+linea;
									DefaultTableModel model2 = (DefaultTableModel)tablaConst.getModel();

									model2.setValueAt(LineaConst, auxEspacio-i, 2);
									flagAConst=true;
								}
								
								
							
							
							
							break;
						}
						
						}
					flagAId=true;
					consta=String.valueOf(auxConst);
					Salida(Numero,Linea,elementArray[vueltas],"2",consta);
					
					
				}else {
					
					contadoridenti++;
				
					constante=constante+1;
					
					consta=String.valueOf(constante);
					espacioTablaOcupado[contadoridenti]=contadoridenti;
					tokenUsado[contadoridenti]=	elementArray[vueltas];
					tokenValorUsado[contadoridenti]=constante;
					
					SalidaConst(elementArray[vueltas],consta,Linea);
					Salida(Numero,Linea,elementArray[vueltas],"2",consta);
				}if(flagError==false) {
					resultado.setText("1:100 Sin errores");
				}
	

		}else if(estado==17){
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String consta=String.valueOf(constante+1);
			String novalido=elementArray[vueltas];
			if(flagError==false) {
				resultado.setText("1:102 Error en la linea: "+Linea+" elemento invalido, "+novalido+" no es valida");
				flagError=true;
			}
		}else if(estado==20){
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String consta=String.valueOf(constante+1);
			String novalido=elementArray[vueltas];
			if(flagError==false) {
				resultado.setText("1:101 Error en la linea: "+Linea+" simbolo desconocido, "+novalido+" no es valida");
				flagError=true;
			}
			
		}else if(estado==19){
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String consta=String.valueOf(constante+1);
			String novalido=elementArray[vueltas];
			if(flagError==false) {
				resultado.setText("1:102 Error en la linea: "+Linea+" elemento invalido, "+novalido+" no es valida");
				flagError=true;
			}
			
		}
		flagExpo=false;
		flagCorreo=false;
		estado=0;
		tam=0;
		cont=0;
		car=' ';
		carMatriz=0;
	}
	
	public void SalidaId(String token, String valor, String linea) {
		tablaSalidaId arrayObjetosid[]=new tablaSalidaId[4];
		 
        //Creamos objetos en cada posicion
		arrayObjetosid[0]=new tablaSalidaId(token,valor,linea);
        
   
        //System.out.println("Identificador "+arrayObjetosid[0].getToken()+" "+"Valor: "+ arrayObjetosid[0].getValor()+" "+"linea: "+arrayObjetosid[0].getLinea());
        DefaultTableModel modelo3 = (DefaultTableModel) tablaId.getModel();
        modelo3.addRow(new Object[] {arrayObjetosid[0].getToken(), arrayObjetosid[0].getValor(), arrayObjetosid[0].getLinea()});
        
        
	}
	public class tablaSalidaId{
		private String token;
		private String valor;
	    private String linea;
	    public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getValor() {
			return valor;
		}
		public void setValor(String valor) {
			this.valor = valor;
		}
		public String getLinea() {
			return linea;
		}
		public void setLinea(String linea) {
			this.linea = linea;
		}
		
		public tablaSalidaId(String token, String valor, String linea) {
			this.token = token;
	        this.valor = valor;
	        this.linea = linea;
	      
	       
		} 
	    
	}
	public void SalidaConst(String token, String valor, String linea){
		tablaSalidaConst arrayObjetosConst[]=new tablaSalidaConst[4];
		 
        //Creamos objetos en cada posicion
		arrayObjetosConst[0]=new tablaSalidaConst(token,valor,linea);
        
   
        //System.out.println("Constante "+arrayObjetosConst[0].getToken()+" "+"Valor: "+ arrayObjetosConst[0].getValor()+" "+"linea: "+arrayObjetosConst[0].getLinea());
        DefaultTableModel modelo2 = (DefaultTableModel) tablaConst.getModel();
        modelo2.addRow(new Object[] {arrayObjetosConst[0].getToken(), arrayObjetosConst[0].getValor(), arrayObjetosConst[0].getLinea()});
	}
	public class tablaSalidaConst{
		private String token;
		private String valor;
	    private String linea;
	    public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getValor() {
			return valor;
		}
		public void setValor(String valor) {
			this.valor = valor;
		}
		public String getLinea() {
			return linea;
		}
		public void setLinea(String linea) {
			this.linea = linea;
		}
		
	    
	    public tablaSalidaConst(String token, String valor, String linea) {
			this.token = token;
	        this.valor = valor;
	        this.linea = linea;
	      
	       
		} 
	    
	}
	public void Salida(String No, String linea, String token, String tipo, String codigo){
		//Creamos un array de objetos de la clase empleados
        Tabla arrayObjetos[]=new Tabla[3];
 
        //Creamos objetos en cada posicion
        arrayObjetos[0]=new Tabla(No, linea, token, tipo,codigo);
       
       
        DefaultTableModel modelo1 = (DefaultTableModel) tablaLexica.getModel();
       // System.out.println("No. "+arrayObjetos[0].getNo()+" "+"Linea: "+arrayObjetos[0].getLinea()+" "+"Token: "+ arrayObjetos[0].getToken()+" "+"Tipo: "+arrayObjetos[0].getTipo()+" "+"Codigo: "+arrayObjetos[0].getCodigo());
        modelo1.addRow(new Object[] {arrayObjetos[0].getNo(), arrayObjetos[0].getLinea(), arrayObjetos[0].getToken(),arrayObjetos[0].getTipo(),arrayObjetos[0].getCodigo()});
        
	}
	
	public class Tabla {

		private String no;
	    private String linea;
	    private String token;
	    private String tipo;
	    private String codigo;
	    
		public String getNo() {
			return no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public String getLinea() {
			return linea;
		}

		public void setLinea(String linea) {
			this.linea = linea;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public Tabla(String no, String linea, String token, String tipo, String codigo) {
			this.no = no;
	        this.linea = linea;
	        this.token = token;
	        this.tipo = tipo;
	        this.codigo = codigo;
	       
		} 
		  
		}
	
	public class Jtable_lexico{
		
		
		/* 
		private String [] cabezera= {"No.","Línea","TOKEN","Tipo","Código"};
		private String [][] datos= {{"1","Fernando","Castillo","Ecuador"},
									{"2","Jorge","monteral","mexicano"},
									{"3","kujo","jotaro","japone"},};
		*/
		public Jtable_lexico() {
			/*
			PanelTablaId.setLayout(new FlowLayout());
			PanelTablaId.setSize(800,600);
			set_table();
			PanelTablaId.setVisible(true);
		*/
		}
		public void set_table() {
			/*PanelTablaId.add(tablaLexica);
			JScrollPane JSlex=new JScrollPane(tablaLexica);
			JSlex.setPreferredSize(new Dimension(400,500));
			PanelTablaId.add(JSlex);
		*/}
	}
	
	public class Jtable_identificadores{
		public JFrame VentanaId;
		
		/* 
		private String [] cabezera= {"No.","Línea","TOKEN","Tipo","Código"};
		private String [][] datos= {{"1","Fernando","Castillo","Ecuador"},
									{"2","Jorge","monteral","mexicano"},
									{"3","kujo","jotaro","japone"},};
		*/
		public Jtable_identificadores() {
			
			
	
			
			
			
		}
		public void set_table() {
			
			
			
		}
	}
	
	public class Jtable_Constantes{
		public JFrame VentanaConst;
		
		/* 
		private String [] cabezera= {"No.","Línea","TOKEN","Tipo","Código"};
		private String [][] datos= {{"1","Fernando","Castillo","Ecuador"},
									{"2","Jorge","monteral","mexicano"},
									{"3","kujo","jotaro","japone"},};
		*/
		public Jtable_Constantes() {
		/*
			VentanaConst.setLayout(new FlowLayout());
			VentanaConst.setSize(800,600);
			set_table();
			VentanaConst.setVisible(true);*/
		}
		public void set_table() {
			/*
			JScrollPane JSlex=new JScrollPane(tablaConst);
			JSlex.setPreferredSize(new Dimension(400,500));
			VentanaConst.add(JSlex);
			*/
		}
	}
	
	
	
}