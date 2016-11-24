package edu.ycp.cs201.webcrawler.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import edu.ycp.cs201.webcrawler.WebCrawlerController;
import edu.ycp.cs201.webcrawler.WebCrawlerModel;

public class WebCrawlerView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Width of the window */
	private static final int WIDTH = 600;
	
	/** Height of window */
	private static final int HEIGHT = 600;
	private WebCrawlerModel model;
	private WebCrawlerController controller;
	
	public WebCrawlerView() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(new GridBagLayout());
	}

	public void setModel(WebCrawlerModel model) {
		this.model = model;
	}

	public void setController(WebCrawlerController controller) {
		this.controller = controller;
	}

}
