package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class DaoGeneratorZhihu {

    public static void main(String[] args) throws Exception{
        Schema schema=new Schema(1,"com.yangyongwen.zhihu3.dao");

        addStory(schema);
        addTopStory(schema);

        new DaoGenerator().generateAll(schema,"app/src/main/java");


    }


    private static void addStory(Schema schema){
        Entity story=schema.addEntity("Story");


        story.addLongProperty("id").primaryKey();
        story.addIntProperty("type");
        story.addBooleanProperty("multipic");
        story.addBooleanProperty("readed");
        story.addStringProperty("title");
        story.addStringProperty("ga_prefix");
        story.addStringProperty("date");
        story.addLongProperty("order");

        Entity image=schema.addEntity("Image");
        Property id=image.addLongProperty("id").getProperty();
        Property url=image.addStringProperty("image_url").getProperty();

        ToMany storyToImages=story.addToMany(image,id);
        storyToImages.setName("images");

    }

    private static void addTopStory(Schema schema){
        Entity topStory=schema.addEntity("TopStory");

        topStory.addLongProperty("id").primaryKey();
        topStory.addIntProperty("type");
        topStory.addBooleanProperty("multipic");
        topStory.addBooleanProperty("readed");
        topStory.addStringProperty("title");
        topStory.addStringProperty("ga_prefix");
        topStory.addStringProperty("date");
        topStory.addStringProperty("image");
        topStory.addLongProperty("order");
    }




}
