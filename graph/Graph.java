/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Scanner;
import java.io.File;
import java.lang.Math;
import java.util.*;

public class Graph{

	int numVertices;
	int numEdges;
	double[][] adjMatrix;
	double[] xCoord;
	double[] yCoord;
	int source;
	int destination;

	Graph(String filename){
		try{
			Scanner sc = new Scanner(new File(filename));
			numVertices = sc.nextInt();
			xCoord = new double[numVertices + 1];
			yCoord = new double[numVertices + 1];
			for(int i=1; i<=numVertices; i++){
				int index = sc.nextInt();
				xCoord[i] = sc.nextDouble();
                             // System.out.println(index);
				yCoord[i] = sc.nextDouble();
			}
			adjMatrix = new double[numVertices + 1][numVertices + 1];
			for(int i=0; i<=numVertices; i++){
				for(int j=0; j<=numVertices; j++){
					adjMatrix[i][j] = 0;
				}
			}
			numEdges = sc.nextInt();
                        
			for(int i=0; i<numEdges; i++){
				int x = sc.nextInt();
				int y = sc.nextInt();
				double d = sc.nextDouble();
				adjMatrix[x][y] = d;
                               // System.out.println(d);
				adjMatrix[y][x] = d;
			}
			source = sc.nextInt();
			destination = sc.nextInt();	
		} catch (Exception e){
			System.out.println(e);
		}
	}

	public void dijkstraAlgo1(){
		double[] distance = new double[numVertices + 1];
		boolean[] visited = new boolean[numVertices + 1];
		int[] parent = new int[numVertices + 1];
		for(int i=0; i<=numVertices; i++){
			distance[i] = 9999.0;
			visited[i] = false;
			parent[i] = -1;
		}
		distance[source] = 0;
		int flag = 0;
		for(int i=0; i<numVertices - 1; i++){
			double minDistance = 999999.0;
			int minVertex = 0;
			for(int j=1; j<=numVertices; j++){
				if(visited[j] == false && distance[j] < minDistance){
					minDistance = distance[j];
					minVertex = j;
				}
			}
			visited[minVertex] = true;
			if(minVertex == destination){
				flag = 1;
				break;
			}
			if(flag == 1){
				break;
			}
			for(int j=1; j<=numVertices; j++){
				if(visited[j] == false && adjMatrix[minVertex][j] != 0 && distance[minVertex] + adjMatrix[minVertex][j] < distance[j]){
					distance[j] = distance[minVertex] + adjMatrix[minVertex][j];
					parent[j] = minVertex;
				}
			}
		}
		System.out.println("Length of shortest path: " + distance[destination]);
		int[] path = new int[numVertices];
		int cnt = 0, temp = parent[destination];
		path[cnt++] = destination;
		while(temp != -1){
			path[cnt++] = temp;
			temp = parent[temp];
		}
		System.out.print("Path taken is: ");
		for(int j=cnt-1; j>=0; j--){
			System.out.print(path[j] + " ");
		}
		System.out.println("");
		int numVisited = 0;
		for(int i=0; i<=numVertices; i++){
			if(visited[i]){
				numVisited++;
			}
		}
		System.out.println("Number of additional vertices added are: " + (numVisited - cnt));
	}

	public void aStarVariant(){
		boolean[] closedList = new boolean[numVertices + 1];
		double[] gValue = new double[numVertices + 1];
		double[] fValue = new double[numVertices + 1];
		double[] hvalue = new double[numVertices + 1];
		int[] parent = new int[numVertices + 1];
		for(int i=0; i<=numVertices; i++){
			closedList[i] = false;
			gValue[i] = 99999;
			hvalue[i] = 99999;
			fValue[i] = 99999;
			parent[i] = -1;
		}
		gValue[source] = 0;
		Set<Integer> openList = new HashSet<Integer>();
		openList.add(source);
		int flag = 0;
		while(!openList.isEmpty()){
			int s = openList.iterator().next();
			openList.remove(s);
			closedList[s] = true;
			for(int i=1; i<=numVertices; i++){
				if(adjMatrix[s][i]!=0 && closedList[i] == false){
					if(i == destination){
						gValue[i] = gValue[s] + adjMatrix[s][i];
						parent[destination] = s;
						flag = 1;
						break;
					}
					double gValueTemp = gValue[s] + adjMatrix[s][i];
					double hvalueTemp = Math.sqrt((xCoord[s]-xCoord[i])*(xCoord[s]-xCoord[i]) + (yCoord[s]-yCoord[i])*(yCoord[s]-yCoord[i]));
					double fValueTemp = gValue[i] + hvalue[i];
					if(fValue[i]==99999 || fValue[i] > fValueTemp){
						openList.add(i);
						gValue[i] = gValueTemp;
						hvalue[i] = hvalueTemp;
						fValue[i] = fValueTemp;
						parent[i] = s;
					}
				}
			}
			if(flag == 1){
				break;
			}
		}
		System.out.println("Length of shortest path: " + gValue[destination]);
		int[] path = new int[numVertices];
		int cnt = 0, temp = parent[destination];
		path[cnt++] = destination;
		while(temp != -1){
			path[cnt++] = temp;
			temp = parent[temp];
		}
		System.out.print("Path taken is: ");
		for(int j=cnt-1; j>=0; j--){
			System.out.print(path[j] + " ");
		}
		System.out.println("");
		int numVisited = 0;
		for(int i=0; i<=numVertices; i++){
			if(closedList[i]){
				numVisited++;
			}
		}
		System.out.println("Number of additional vertices added are: " + (numVisited - cnt));

	}

	public void dijkstraAlgo3(){
		double[] distance = new double[numVertices + 1];
		boolean[] visited = new boolean[numVertices + 1];
		int[] parent = new int[numVertices + 1];
		double[] distance1 = new double[numVertices + 1];
		boolean[] visited1 = new boolean[numVertices + 1];
		int[] parent1 = new int[numVertices + 1];
		for(int i=0; i<=numVertices; i++){
			distance[i] = 9999.0;
			visited[i] = false;
			parent[i] = -1;
			distance1[i] = 9999.0;
			visited1[i] = false;
			parent1[i] = -1;
		}
		distance[source] = 0;
		distance1[destination] = 0;
		int flag = 0;
		int commonVertex = 0;
		for(int i=0; i<numVertices - 1; i++){
			double minDistance = 999999.0;
			int minVertex = 0;
			double minDistance1 = 999999.0;
			int minVertex1 = 0;
			for(int j=1; j<=numVertices; j++){
				if(visited[j] == false && distance[j] < minDistance){
					minDistance = distance[j];
					minVertex = j;
				}
				if(visited1[j] == false && distance1[j] < minDistance1){
					minDistance1 = distance1[j];
					minVertex1 = j;
				}
			}
			visited[minVertex] = true;
			visited1[minVertex1] = true;
			for(int j=1; j<=numVertices; j++){
				if(visited[j] && visited1[j]){
					flag = 1;
					commonVertex = j;
					System.out.println("Common: " + commonVertex);
					break;
				}
			}
			if(flag == 1){
				break;
			}
			for(int j=1; j<=numVertices; j++){
				if(visited[j] == false && adjMatrix[minVertex][j] != 0 && distance[minVertex] + adjMatrix[minVertex][j] < distance[j]){
					distance[j] = distance[minVertex] + adjMatrix[minVertex][j];
					parent[j] = minVertex;
				}
				if(visited1[j] == false && adjMatrix[minVertex1][j] != 0 && distance1[minVertex1] + adjMatrix[minVertex1][j] < distance1[j]){
					distance1[j] = distance1[minVertex1] + adjMatrix[minVertex1][j];
					parent1[j] = minVertex1;
				}
			}
		}
		System.out.println("Length of shortest path: " + (distance[commonVertex] + distance1[commonVertex]));
		int[] path = new int[numVertices];
		int cnt = 0, cnt1, temp = parent1[commonVertex];
		path[cnt++] = commonVertex;
		while(temp != -1){
			path[cnt++] = temp;
			temp = parent1[temp];
		}
		cnt1 = cnt;
		temp = parent[commonVertex];
		while(temp != -1){
			path[cnt++] = temp;
			temp = parent[temp];
		}
		System.out.print("Path taken is: ");
		for(int j=cnt-1; j>=cnt1; j--){
			System.out.print(path[j] + " ");
		}
		for(int j=0; j<=cnt1-1; j++){
			System.out.print(path[j] + " ");
		}
		System.out.println("");
		int numVisited = 0;
		for(int i=0; i<=numVertices; i++){
			if(visited[i]){
				numVisited++;
			}
			if(visited1[i]){
				numVisited++;
			}
		}
		System.out.println("Number of additional vertices added are: " + (numVisited - cnt - 1));
	}

	public static void main(String[] args) {
		Graph g = new Graph("source.txt");
		System.out.println("For Djikstra's Algorithm -->");
		g.dijkstraAlgo1();
		System.out.println("\n\nFor A* Algorithm -->");
		g.aStarVariant();
		System.out.println("\n\nFor Improved Djikstra's Algorithm -->");
		g.dijkstraAlgo3();
	}
}