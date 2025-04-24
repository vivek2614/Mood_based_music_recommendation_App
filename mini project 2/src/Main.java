import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.net.URI;

class MoodMusicRecommender {
    private JFrame frame;
    private JComboBox<String> moodBox;
    private JLabel resultLabel;
    private Map<String, String> moodToSong;

    public MoodMusicRecommender() {
        // Mood-to-Song Map
        moodToSong = new HashMap<>();
        moodToSong.put("Happy", "https://www.youtube.com/watch?v=nJZcbidTutE");
        moodToSong.put("Sad", "https://www.youtube.com/watch?v=lBvbNxiVmZA");
        moodToSong.put("Energetic", "https://www.youtube.com/watch?v=zC3UbTf4qrM");
        moodToSong.put("Relaxed", "https://www.youtube.com/watch?v=1sRaLqtHXQU");
        moodToSong.put("Romantic", "https://www.youtube.com/watch?v=siw7-MTgE4s");

        frame = new JFrame("Mood-Based Music Recommender");
        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Select your Mood:");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        moodBox = new JComboBox<>(moodToSong.keySet().toArray(new String[0]));
        moodBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton getSongButton = new JButton("Get Song");
        getSongButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultLabel = new JLabel("🎵 Your recommended song will appear here.");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        getSongButton.addActionListener(e -> recommendSong());

        // Layout using BoxLayout for vertical centering
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(20)); // spacing
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(moodBox);
        panel.add(Box.createVerticalStrut(10));
        panel.add(getSongButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(resultLabel);
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setLocationRelativeTo(null); // center the frame on screen
        frame.setVisible(true);
    }

    private void recommendSong() {
        String selectedMood = (String) moodBox.getSelectedItem();
        String songURL = moodToSong.get(selectedMood);

        try {
            Desktop.getDesktop().browse(new URI(songURL));
            resultLabel.setText("🎧 Playing: " + selectedMood + " vibes");
        } catch (Exception ex) {
            resultLabel.setText("❌ Unable to open link.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MoodMusicRecommender::new);
    }
}
