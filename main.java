import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        // Crear la ventana
        JFrame frame = new JFrame("App del Tiempo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Layout para organizar los componentes
        frame.setLayout(new FlowLayout());

        // Crear los componentes
        JLabel cityLabel = new JLabel("Ciudad:");
        JTextField cityField = new JTextField(15);
        JButton searchButton = new JButton("Buscar");
        JTextArea weatherArea = new JTextArea(5, 30);
        weatherArea.setEditable(false);

        // Añadir los componentes al frame
        frame.add(cityLabel);
        frame.add(cityField);
        frame.add(searchButton);
        frame.add(new JScrollPane(weatherArea)); // Scroll para el área de texto

        // Acción del botón
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeatherService service = new WeatherService("[Tu Clave API]");
                try {
                    String weatherData = service.getWeatherData(cityField.getText());
                    weatherArea.setText(weatherData);
                } catch (Exception ex) {
                    weatherArea.setText("Error al obtener los datos del tiempo. ¿Ingresaste una ciudad válida?");
                }
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }
}
