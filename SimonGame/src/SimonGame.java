
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Component;
import java.util.ArrayList;

public class SimonGame extends JFrame {
	private JPanel contentPane;
	private ArrayList<Integer> sequence;
	private ArrayList<Integer> userSequence = new ArrayList<Integer>();
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JLabel lblNewLabel_1;
	int score = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimonGame frame = new SimonGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimonGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		JPanel menuPanel = new JPanel();
		contentPane.add(menuPanel, "menuPanel");
		menuPanel.setLayout(new BorderLayout(0, 0));
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(34, 139, 34));
		menuPanel.add(btnNewButton, BorderLayout.CENTER);
		JPanel gamePanel = new JPanel();
		contentPane.add(gamePanel, "gamePanel");
		gamePanel.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel("Simon Says");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gamePanel.add(lblNewLabel, BorderLayout.NORTH);
		JPanel buttonsPanel = new JPanel();
		gamePanel.add(buttonsPanel, BorderLayout.CENTER);
		buttonsPanel.setLayout(new GridLayout(2, 2, 0, 0));
		btnNewButton_1 = new JButton("1");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String curText = ((JButton) e.getSource()).getText();
				userSequence.add(Integer.parseInt(curText));
				checkForMatch();
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.RED);
		buttonsPanel.add(btnNewButton_1);
		btnNewButton_2 = new JButton("2");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String curText = ((JButton) e.getSource()).getText();
				userSequence.add(Integer.parseInt(curText));
				checkForMatch();
			}
		});
		btnNewButton_2.setBackground(Color.GREEN);
		buttonsPanel.add(btnNewButton_2);
		btnNewButton_3 = new JButton("3");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String curText = ((JButton) e.getSource()).getText();
				userSequence.add(Integer.parseInt(curText));
				checkForMatch();
			}
		});
		btnNewButton_3.setBackground(Color.BLUE);
		buttonsPanel.add(btnNewButton_3);
		btnNewButton_4 = new JButton("4");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String curText = ((JButton) e.getSource()).getText();
				userSequence.add(Integer.parseInt(curText));
				checkForMatch();
			}
		});
		btnNewButton_4.setBackground(Color.YELLOW);
		buttonsPanel.add(btnNewButton_4);
		lblNewLabel_1 = new JLabel("Score");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		gamePanel.add(lblNewLabel_1, BorderLayout.SOUTH);
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.show(contentPane, "gamePanel");
				startGame();
			}
		});
	}

// The logic that is run to start or setup the game.
	public void startGame() {
		sequence = new ArrayList<Integer>();
		addToSequence();
	}

// Generate the numbers that Simon will "say", so we can flash them to the  player
	public void addToSequence() {
		int min = 1;
		int max = 4;
		int randomNum = (int) (Math.random() * (max - min + 1)) + 1;
		System.out.println(randomNum);
		sequence.add(randomNum);
		showSequence();
	}

	public void showSequence() {
		flickerButton(sequence.get(0));
	}

	public void flickerButton(int buttonNumber) {
		JButton curButton = new JButton();
		Color originalColor = new Color(buttonNumber);
		switch (buttonNumber) {
		case 1:
			curButton = btnNewButton_1;
			originalColor = btnNewButton_1.getBackground();
			break;
		case 2:
			curButton = btnNewButton_2;
			originalColor = btnNewButton_2.getBackground();
			break;
		case 3:
			curButton = btnNewButton_3;
			originalColor = btnNewButton_3.getBackground();
			break;
		case 4:
			curButton = btnNewButton_4;
			originalColor = btnNewButton_4.getBackground();
			break;
		}
		try {
// Flicker the button's background color to white for 1 second
			contentPane.paintImmediately(0, 0, getWidth(), getHeight());
			Thread.sleep(1000);
			curButton.setBackground(new Color(250, 250, 250));
			contentPane.paintImmediately(0, 0, getWidth(), getHeight());
			Thread.sleep(1000);
			curButton.setBackground(originalColor);
			contentPane.paintImmediately(0, 0, getWidth(), getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkForMatch() {
		boolean hasMatch = sequence.get(0) == userSequence.get(0);
		if (hasMatch) {
			score++;
			lblNewLabel_1.setText("Score: " + score);
		} else {
			lblNewLabel_1.setText("You lose!");
		}
	}
}