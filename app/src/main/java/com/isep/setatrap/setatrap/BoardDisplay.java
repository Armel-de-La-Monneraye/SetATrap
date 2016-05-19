package com.isep.setatrap.setatrap;


import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.TextView;


import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.*;


import java.util.List;


import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class BoardDisplay extends Activity {
    private GridLayout myGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_board_display);
        DrawPieces board = new DrawPieces(this);
        setContentView(board);
    }



    public void lire(String fichier)
    {
        try
        {
            SAXBuilder saxBuilder = new SAXBuilder();
            System.out.println("Ouverture fichier");

            //File file = new File(fichier);
            InputStream file = this.getAssets().open("/mnt/sdcard/activity_board_display.xml") ;
           // FileInputStream inFile = getResources().getXml(R.layout.activity_board_display);
            System.out.println("après ouverture");

            Document document = saxBuilder.build(file);
            System.out.println("après sax");

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
