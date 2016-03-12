package com.bitkeks.ckq;

import java.util.ArrayList;

public class Maze {
	// Params
	int eventnum = 40;
	int eventsAtTime = 1;
	int cellsX = 20;
	int cellsY = 20;
	int randomSeed = 1337;

	// Finished maze fields
	int layernum = 41;
	int tiles[][][];
	int cacheIDs[];
	boolean generated = false;
	ArrayList<Event> events = new ArrayList<Event>();

}
