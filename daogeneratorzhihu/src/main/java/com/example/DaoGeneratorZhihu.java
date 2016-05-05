package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGeneratorZhihu {

    public static void main(String[] args) throws Exception{
        Schema schema=new Schema(1,"com.yangyongwen.zhihu3.dao");

        addStory(schema);

        new DaoGenerator().generateAll(schema,"app/src/main/java");


    }


    private static void addStory(Schema schema){
        Entity story=schema.addEntity("Story");

        story.addIntProperty("id").primaryKey();
        story.addIntProperty("type");
        story.addBooleanProperty("multipic");
        story.addBooleanProperty("readed");
        story.addStringProperty("title");
        story.addStringProperty("images");
        story.addStringProperty("ga_prefix");
        story.addStringProperty("date");

    }




}
