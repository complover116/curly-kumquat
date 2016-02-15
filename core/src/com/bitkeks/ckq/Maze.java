package com.bitkeks.ckq;

import java.util.ArrayList;

public class Maze {
	// Params
	int eventnum = 19;
	int eventsAtTime = 1;
	int cellsX = 30;
	int cellsY = 30;
	int randomSeed = 1337;

	// Finished maze fields
	int layernum = 20;
	int tiles[][][];
	int cacheIDs[];
	boolean generated = false;
	ArrayList<Event> events = new ArrayList<Event>();

}
