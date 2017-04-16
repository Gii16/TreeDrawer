package ucas.gii.parseDrawer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.Tree;
import lombok.Setter;
/**
 * @Description
 * main class to draw tree
 * @author gii
 * @date 2017年4月16日
 */
public class ParseTreeDrawer {

	private PrintWriter pw;
	@Setter
	private boolean toConsole;

	public ParseTreeDrawer() {
		this(true, null);
	}

	public ParseTreeDrawer(File file, boolean toConsole) throws IOException {
		this(toConsole, new PrintWriter(new FileWriter(file)));
	}

	private ParseTreeDrawer(boolean toConsole, PrintWriter pw) {
		this.pw = pw;
		this.toConsole = toConsole;
	}

	public void close() {
		if (pw != null) {
			pw.close();
		}
	}

	public void draw(Tree tree) throws IOException {
		if (!this.toConsole && this.pw == null) {
			throw new IOException("No place to draw");
		}
		draw(this.pw, new VisualTree(tree));
	}

	public void draw(SemanticGraph tree) throws IOException {
		if (!this.toConsole && this.pw == null) {
			throw new IOException("No place to draw");
		}
		draw(this.pw, new VisualTree(tree));
	}

	public void draw(Tree tree, File file) throws IOException {
		draw(new PrintWriter(new FileWriter(file)), new VisualTree(tree));
	}

	public void draw(SemanticGraph tree, File file) throws IOException {
		draw(new PrintWriter(new FileWriter(file)), new VisualTree(tree));
	}

	public String getTreeContent(SemanticGraph tree) {
		return new VisualTree(tree).getVisualContent();
	}

	public String getTreeContent(Tree tree) {
		return new VisualTree(tree).getVisualContent();
	}

	private void draw(PrintWriter pw, VisualTree vTree) {

		String toOutput = vTree.getVisualContent();
		if (pw != null) {
			pw.println(toOutput);
			pw.flush();
		}
		if (toConsole) {
			System.out.println(toOutput);
		}
	}

}
