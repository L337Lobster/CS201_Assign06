package edu.ycp.cs201.webcrawler.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import edu.ycp.cs201.webcrawler.WebCrawlerModel;
import edu.ycp.cs201.webcrawler.WebCrawlerController;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				WebCrawlerView view = new WebCrawlerView();
				
				WebCrawlerController controller = new WebCrawlerController();
				
				WebCrawlerModel model = new WebCrawlerModel();
				
				view.setModel(model);
				view.setController(controller);
				
				JFrame frame = new JFrame("Web Crawler");
				frame.setContentPane(view);
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
			}
		});

	}

}
