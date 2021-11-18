package org.protimagerenderer;
import java.io.IOException;

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.StructureIO;
import org.biojava.nbio.structure.align.gui.jmol.StructureAlignmentJmol;
public class ProtImageRenderer {

	public static void main(String[] args) {
		fetchAndConvert(args[0], args[1]);
	}
	
	public static void fetchAndConvert(String protName, String fileName) {
		try {
			Structure targetStructure = StructureIO.getStructure(protName);
			StructureAlignmentJmol jmolPanel = new StructureAlignmentJmol();
			jmolPanel.setStructure(targetStructure);
			//the following three lines are for demonstration purposes only
			jmolPanel.evalString("select *; color chain;");
			jmolPanel.evalString("select *; spacefill off; wireframe off; cartoon on;");
			jmolPanel.evalString("select ligands; cartoon off; wireframe 0.3; spacefill 0.5; color cpk;");
			//specify the command to export the image
			String exportString = "write image png \"" + fileName + "\"";
			jmolPanel.evalString(exportString);
		} catch (StructureException e) {
			System.out.println("Structure Exception:");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException:");
			e.printStackTrace();
		}
		
	}

}
