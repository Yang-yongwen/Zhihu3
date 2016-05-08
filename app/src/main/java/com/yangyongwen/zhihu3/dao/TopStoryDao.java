package com.yangyongwen.zhihu3.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.yangyongwen.zhihu3.dao.TopStory;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TOP_STORY".
*/
public class TopStoryDao extends AbstractDao<TopStory, Long> {

    public static final String TABLENAME = "TOP_STORY";

    /**
     * Properties of entity TopStory.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property Type = new Property(1, Integer.class, "type", false, "TYPE");
        public final static Property Multipic = new Property(2, Boolean.class, "multipic", false, "MULTIPIC");
        public final static Property Readed = new Property(3, Boolean.class, "readed", false, "READED");
        public final static Property Title = new Property(4, String.class, "title", false, "TITLE");
        public final static Property Ga_prefix = new Property(5, String.class, "ga_prefix", false, "GA_PREFIX");
        public final static Property Date = new Property(6, String.class, "date", false, "DATE");
        public final static Property Image = new Property(7, String.class, "image", false, "IMAGE");
    };


    public TopStoryDao(DaoConfig config) {
        super(config);
    }
    
    public TopStoryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TOP_STORY\" (" + //
                "\"ID\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TYPE\" INTEGER," + // 1: type
                "\"MULTIPIC\" INTEGER," + // 2: multipic
                "\"READED\" INTEGER," + // 3: readed
                "\"TITLE\" TEXT," + // 4: title
                "\"GA_PREFIX\" TEXT," + // 5: ga_prefix
                "\"DATE\" TEXT," + // 6: date
                "\"IMAGE\" TEXT);"); // 7: image
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TOP_STORY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, TopStory entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(2, type);
        }
 
        Boolean multipic = entity.getMultipic();
        if (multipic != null) {
            stmt.bindLong(3, multipic ? 1L: 0L);
        }
 
        Boolean readed = entity.getReaded();
        if (readed != null) {
            stmt.bindLong(4, readed ? 1L: 0L);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String ga_prefix = entity.getGa_prefix();
        if (ga_prefix != null) {
            stmt.bindString(6, ga_prefix);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(7, date);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(8, image);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public TopStory readEntity(Cursor cursor, int offset) {
        TopStory entity = new TopStory( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // type
            cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0, // multipic
            cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0, // readed
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // title
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // ga_prefix
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // date
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // image
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, TopStory entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setMultipic(cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0);
        entity.setReaded(cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0);
        entity.setTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setGa_prefix(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDate(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setImage(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(TopStory entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(TopStory entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}