package edu.project2;

import edu.project2.generator.Generator;
import edu.project2.generator.PrimsMazeGenerator;
import edu.project2.model.Maze;
import edu.project2.printer.MazePrinter;
import edu.project2.printer.Renderer;

public class Main {
    public static void main(String[] args) {
        Generator gen = new PrimsMazeGenerator(21, 21);
        Maze maze = gen.generate(21, 21);
        Renderer printer = new MazePrinter();
        System.out.println(printer.render(maze));
    }
}
