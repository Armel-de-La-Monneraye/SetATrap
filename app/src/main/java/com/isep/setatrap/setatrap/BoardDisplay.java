package com.isep.setatrap.setatrap;


import android.app.Activity;
import android.os.Bundle;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;

public class BoardDisplay extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lire("src/main/res/layout/activity_board_display.xml");
        System.out.println("après lire");
        setContentView(R.layout.activity_board_display);
    }

    public void lire(String fichier)
    {
        try
        {
            SAXBuilder saxBuilder = new SAXBuilder();
            System.out.println("Ouverture fichier");

            //File file = new File(fichier);
            InputStream file = this.getAssets().open("activity_board_display.xml") ;
            FileInputStream inFile = getResources().getXml(R.layout.activity_board_display);
            System.out.println("après ouverture");
            Document document = saxBuilder.build(file);

            Element racine = document.getRootElement();
            Element gridLayout = racine.getChild("GridLayout");
            Element firstButton = new Element("Button");
            firstButton.setAttribute("id","@+id/firstButton");
            firstButton.setAttribute("layout_width","wrap_content");
            firstButton.setAttribute("layout_height","wrap_content");
            gridLayout.addContent(firstButton);

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(fichier));

        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}
