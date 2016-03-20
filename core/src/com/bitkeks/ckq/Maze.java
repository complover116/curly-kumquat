package com.bitkeks.ckq;

import java.util.ArrayList;

public class Maze {
	// Params
	int eventnum = 5;
	int eventsAtTime = 1;
	int cellsX = 15;
	int cellsY = 15;
	int randomSeed = 1337;

	// Finished maze fields
	int layernum = 31;
	int tiles[][][];
	int cacheIDs[];
	boolean generated = false;
	ArrayList<Event> events = new ArrayList<Event>();

}
