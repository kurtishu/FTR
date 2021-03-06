package com.dreamfactory.ftr;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dreamfactory.ftr.FilePaserManager.Callback;

public class MainViewController {

	private JFrame mainFrame;
	private JTextField inputTextField;
	private JButton btnStart;

	public static void main(String[] args) {
		MainViewController mainCtr = new MainViewController();
		mainCtr.prepareGUI();
	}

	public void prepareGUI() {
		mainFrame = new JFrame("FTR");
		mainFrame.setSize(400, 130);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		JLabel headerLabel = new JLabel("   Please enter your dir path:",
				JLabel.LEFT);

		inputTextField = new JTextField(28);
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("filePath:" + System.getProperty("user.dir"));
				
				validateInputPath();
			}
		});

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(inputTextField);
		controlPanel.add(btnStart);

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		showInCenter(mainFrame);
	}

	private void showInCenter(JFrame mainFrame) {
		 Toolkit kit = Toolkit.getDefaultToolkit();    
	     Dimension screenSize = kit.getScreenSize();  
	     int screenWidth = screenSize.width / 2;       
	     int screenHeight = screenSize.height / 2;      
	     int height = mainFrame.getHeight();
	     int width = mainFrame.getWidth();
	     mainFrame.setLocation(screenWidth-width/2, screenHeight-height/2);
	}
	
	/**
	 * Validate the input text, show error alert if invalid.
	 */
	private void validateInputPath() {
		String path = inputTextField.getText().trim();

		boolean isValidate = false;
		if (null != path && path.length() > 0) {
			File dirPath = new File(path);
			if (null != dirPath && dirPath.exists()) {
				isValidate = true;
			}
		}

		// Show error alert
		if (!isValidate) {
			JOptionPane.showMessageDialog(mainFrame, "请输入正确的路径！", "出错了哦",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Start parse files
		new FilePaserManager(path).start(new Callback() {

			@Override
			public void success() {
				//JOptionPane.showMessageDialog(mainFrame, "文件导出成功", "恭喜恭喜",
				//		JOptionPane.CLOSED_OPTION);
				int option = JOptionPane.showConfirmDialog(mainFrame, "文件导出成功", "恭喜恭喜", JOptionPane.CLOSED_OPTION);
				if(option == JOptionPane.OK_OPTION) {
					System.exit(0);
			    }
			
			}});
	}
}
