package com.example.tanut.mapsearch;

import com.example.tanut.mapsearch.data.db.MyItemReader;
import com.example.tanut.mapsearch.data.db.model.MyItem;
import com.example.tanut.mapsearch.ui.map.MapMvpView;
import com.example.tanut.mapsearch.ui.map.MapPresenterImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.verify;

/**
 * Created by tanut on 10/19/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MapPresenterImplTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    MapMvpView view ;

    @Mock
    MyItemReader mReader;

    private MapPresenterImpl presenter;
    private static final List<MyItem> MANY_ITEM = Arrays.asList(new MyItem(0,0), new MyItem(0,0), new MyItem(0,0));

    @Before
    public void setUp() throws Exception {
        presenter = new MapPresenterImpl(view,mReader);
    }

    @Test
    public void shouldPassItems() {
        Mockito.when(mReader.read(null)).thenReturn(MANY_ITEM);
        presenter.getGeoPlaceData("TEST",null);
        verify(view).showMarkerCluster(MANY_ITEM);
    }

    @Test
    public void shouldHandleNoItemFound() {
        Mockito.when(mReader.read(null)).thenReturn(EMPTY_LIST);
        presenter.getGeoPlaceData("TEST",null);
        verify(view).showMessage("NO ITEM");
    }
/*
    @Test
    public void shouldHandleerror() {
        Mockito.when(mReader.read(null)).thenThrow(new RuntimeException());
        presenter.getGeoPlaceData("TEST",null);
        verify(view).showMessage("ON ERROR");
    }*/
    @After
    public void cleanUp() {
    }
}
