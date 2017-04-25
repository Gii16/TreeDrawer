package ucas.gii.parseDrawer;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
/**
 * @Description
 * easy example for telling user how to draw a StanfordTree
 * @author gii
 * @date 2017年4月15日
 */
public class Example {
	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, parse");
		StanfordCoreNLP coreNlp = new StanfordCoreNLP(props);

		String str = "It is a example,you can use the tool like that. Thanks!";
		Annotation document = new Annotation(str);
		coreNlp.annotate(document);
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		// 上面部分是Stanford CoreNlp的操作过程
		// the part above is the operation in Stanford CoreNlp

		ParseTreeDrawer drawer = new ParseTreeDrawer();
		for (CoreMap sentence : sentences) {
			drawer.draw(sentence.get(TreeCoreAnnotations.TreeAnnotation.class));
			drawer.draw(sentence.get(CollapsedCCProcessedDependenciesAnnotation.class));
		}

	}
}
