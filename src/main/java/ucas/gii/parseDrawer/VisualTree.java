package ucas.gii.parseDrawer;

import java.util.List;
import java.util.Queue;

import com.google.common.collect.Lists;

import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.Tree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 输出各类树的数据结构 将各类树结构转化为VisualTree，便于输出
 * @author gii
 * @date 2017年4月16日
 */
@AllArgsConstructor
public class VisualTree {
	private static final char _SPACE = ' ';
	private static final char _LINE = (char) 9590;
	private static final char LEFT_BAR = (char) 9581;
	private static final char RIGHT_BAR = (char) 9582;
	private static final char SINGLE_BAR = '|';
	private static final char CONTINUED_BAR = (char) 9516;
	private static final int SPACE_LEN = 2;

	private int left;
	@Getter
	private VisualTree children[];
	@Setter
	@Getter
	private String content;
	private String label;

	public VisualTree(Tree tree) {
		this(tree, 0);
	}

	public VisualTree(SemanticGraph tree) {
		this(tree.getFirstRoot(), tree, 0);
	}

	public String getVisualContent() {
		int levelLen = 1;
		int levelCount = 0;
		int childCount = 0;
		StringBuffer sb = new StringBuffer();
		StringBuffer lineSb = new StringBuffer();
		StringBuffer allSb = new StringBuffer();
		Queue<VisualTree> toOperate = Lists.newLinkedList();
		toOperate.offer(this);
		allSb.append(this.content + "\r\n");
		while (toOperate.size() > 0) {
			VisualTree op = toOperate.poll();
			VisualTree[] children = op.getChildren();
			int spaceLen = op.left;
			for (int i = 0; i < children.length; i++) {// child和child之间填补空格，linesb是当前这些子节点上面的线
				VisualTree child = children[i];
				String content = child.content;
				if (sb.length() < spaceLen) {
					sb.append(getFill(_SPACE, spaceLen - sb.length()));
					lineSb.append(getFill(_SPACE, spaceLen - lineSb.length()));
				}
				lineSb.append(getLine(content.length(), children.length - 1, i));
				sb.append(content);
				toOperate.offer(child);
			}
			childCount += children.length;
			levelCount++;
			if (levelCount == levelLen) {
				allSb.append(lineSb + "\r\n");
				allSb.append(sb + "\r\n");
				lineSb.setLength(0);
				sb.setLength(0);
				levelCount = 0;
				levelLen = childCount;
				childCount = 0;
			}
		}
		return allSb.toString();
	}
	private VisualTree(Tree tree, int leftOffset) {
		init(tree, leftOffset);
	}

	private VisualTree(IndexedWord tree, SemanticGraph root, int leftOffset) {
		init(tree, root, leftOffset);
	}
	
	private void init(Tree tree, int left) {
		this.label = tree.label().value();
		this.left = left;
		Tree[] oriChildren = tree.children();
		int width = 0;
		this.children = oriChildren.length > 0 ? new VisualTree[tree.children().length] : new VisualTree[0];
		for (int i = 0; i < oriChildren.length; i++) {
			this.children[i] = new VisualTree(oriChildren[i], this.left + width);
			width += this.children[i].content.length();
		}
		if (oriChildren.length > 0 && width < label.length()) {
			this.children[this.children.length - 1].setContent(
					getFill(_SPACE, label.length() - width) + this.children[this.children.length - 1].getContent());
			width = label.length();
		}
		int spaceLen = oriChildren.length > 0 ? (width - label.length() + 1) / 2 : SPACE_LEN;
		this.content = getFill(_SPACE, spaceLen) + label + getFill(_SPACE, spaceLen);
	}

	private void init(IndexedWord tree, SemanticGraph root, int left) {
		this.label = tree.toString();
		this.left = left;
		root.edgeCount();
		List<IndexedWord> oriChildren = root.getChildList(tree);
		int width = 0;
		this.children = oriChildren.size() > 0 ? new VisualTree[root.outDegree(tree)] : new VisualTree[0];
		for (int i = 0; i < oriChildren.size(); i++) {
			IndexedWord child = oriChildren.get(i);
			this.children[i] = initRelation(tree, child, root, this.left + width);
			width += this.children[i].content.length();
		}
		if (oriChildren.size() > 0 && width < label.length()) {
			this.children[this.children.length - 1].setContent(
					getFill(_SPACE, label.length() - width) + this.children[this.children.length - 1].getContent());
			width = label.length();
		}
		int spaceLen = oriChildren.size() > 0 ? (width - label.length() + 1) / 2 : SPACE_LEN;
		this.content = getFill(_SPACE, spaceLen) + label + getFill(_SPACE, spaceLen);
	}

	private VisualTree initRelation(IndexedWord tree, IndexedWord child, SemanticGraph root, int left) {
		String label = root.getEdge(tree, child).getRelation().toString();
		VisualTree childNode = new VisualTree(child, root, left);
		int spaceLen = (childNode.content.length() - label.length() + 1) / 2;
		VisualTree relation = new VisualTree(left, new VisualTree[] { childNode },
				getFill(_SPACE, spaceLen) + label + getFill(_SPACE, spaceLen), label);
		return relation;
	}

	private StringBuffer getLine(int contentLen, int end, int contentNum) {
		StringBuffer lineSb = new StringBuffer();
		int frontLen = contentLen / 2;
		int backLen = contentLen % 2 == 0 ? contentLen / 2 - 1 : contentLen / 2;
		if (end == 0) {
			lineSb.append(getFill(_SPACE, frontLen));
			lineSb.append(SINGLE_BAR);
			lineSb.append(getFill(_SPACE, backLen));
		} else if (contentNum == 0) {
			lineSb.append(getFill(_SPACE, frontLen));
			lineSb.append(LEFT_BAR);
			lineSb.append(getFill(_LINE, backLen));
		} else if (contentNum == end) {
			lineSb.append(getFill(_LINE, frontLen));
			lineSb.append(RIGHT_BAR);
			lineSb.append(getFill(_SPACE, backLen));
		} else {
			lineSb.append(getFill(_LINE, frontLen));
			lineSb.append(CONTINUED_BAR);
			lineSb.append(getFill(_LINE, backLen));
		}
		return lineSb;
	}

	private static StringBuffer getFill(char fill, int n) {
		StringBuffer sb = new StringBuffer();
		while (n-- > 0)
			sb.append(fill);
		return sb;
	}

}