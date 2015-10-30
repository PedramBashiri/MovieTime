package com.example.pedram.movietime;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Nitin1992 on 29-04-2015.
 */
public class Parser {

    static public class SaxParser extends DefaultHandler {

        Mov newApp;

        static public Mov getInput(InputStream in)
                throws IOException, SAXException {
            SaxParser parser = new SaxParser();
            Xml.parse(in, Xml.Encoding.UTF_8, parser);
            return parser.getapps();
        }

        public Mov getapps() {
            return newApp;
        }

        @Override
        public void startDocument() throws SAXException {
            // TODO Auto-generated method stub
            super.startDocument();
            newApp = new Mov();

        }

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            // TODO Auto-generated method stub
            super.startElement(uri, localName, qName, attributes);

            Log.d("sax", localName);
            if (localName.equals("movie")) {
                newApp.setTitle(attributes.getValue("title"));
                newApp.setPlot(attributes.getValue("plot"));
                newApp.setImg(attributes.getValue("poster"));
                newApp.setRatings(attributes.getValue("imdbRating"));

            }

        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {


           // if (localName.equals("movie")) {
             //   apps.add(newApp);
               // newApp = new Mov();            }
            // TODO Auto-generated method stub
            super.endElement(uri, localName, qName);

        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {

            // TODO Auto-generated method stub
            super.characters(ch, start, length);

        }

    }

}
