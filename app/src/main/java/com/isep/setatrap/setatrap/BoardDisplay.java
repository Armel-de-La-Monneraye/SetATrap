package com.isep.setatrap.setatrap;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;


public class BoardDisplay extends AppCompatActivity {
    static org.jdom2.Document document;
    static Element racine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lire("res/layout/activity_board_display.xml");
        System.out.println("après lire");
        setContentView(R.layout.activity_board_display);
    }

    public static void createBouton() {

        XMLOutputter outputter = new XMLOutputter();
        try {

            Element bouton = new Element("button");

            Attribute width = new Attribute("layout_width", "wrap_content");
            Attribute height = new Attribute("layout_height", "wrap_content");
            Attribute text = new Attribute("text", "ca marche");

            bouton.setAttribute(width);
            bouton.setAttribute(height);
            bouton.setAttribute(text);

            racine.addContent(bouton);
            outputter.output(bouton, System.out);

        } catch (IOException e) {
        }
    }


    public static void lire(String fichier)
    {
        try
        {
            System.out.println("etape 1");
            SAXBuilder saxBuilder = new SAXBuilder();
            System.out.println("etape 2");

            document = saxBuilder.build(new File(fichier));

            System.out.println("etape 3");
            Element racine = document.getRootElement();
            System.out.println("etape 4");
            Element gridLayout = racine.getChild("GridLayout");

            Element firstButton = new Element("Button");
            firstButton.setAttribute("id","@+id/firstButton");
            firstButton.setAttribute("layout_width","wrap_content");
            firstButton.setAttribute("layout_height","wrap_content");
            gridLayout.addContent(firstButton);

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(fichier));
            System.out.println("etape 5");

        }
        catch(Exception e){
            System.out.println(e);
        }

    }



    public static void enregistre(String fichier)
    {
        try
        {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
            //avec en argument le nom du fichier pour effectuer la sérialisation.
            sortie.output(document, new FileOutputStream(fichier));
        }
        catch (java.io.IOException e){}
    }
}
