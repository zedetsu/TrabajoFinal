package upc.grupo1;
import java.util.Scanner;

public class TiendaHaku {
    private static String[] productos = {"Otaku Anime", "Otaku Manga", "Otaku Idols", "Otaku Militar"};
    private static double[] costos = {17, 15, 12, 8};
    private static String[] tallas = {"S", "M", "L"};
    private static String[] colores = {"Blanco", "Negro"};
    private static int[][][] stock = {
            {
                    {10, 15, 20},  // Otaku Anime (S, M, L) - Blanco
                    {5, 8, 12}     // Otaku Anime (S, M, L) - Negro
            },
            {
                    {8, 12, 18},   // Otaku Manga (S, M, L) - Blanco
                    {4, 6, 10}     // Otaku Manga (S, M, L) - Negro
            },
            {
                    {6, 9, 15},    // Otaku Idols (S, M, L) - Blanco
                    {3, 5, 9}      // Otaku Idols (S, M, L) - Negro
            },
            {
                    {12, 18, 24},  // Otaku Militar (S, M, L) - Blanco
                    {10, 15, 20}   // Otaku Militar (S, M, L) - Negro
            }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println("Bienvenido al sistema de gestión de pedidos de Haku");
            System.out.println("1. Realizar pedido");
            System.out.println("2. Verificar stock");
            System.out.println("3. Salir");
            System.out.print("Ingrese la opción deseada: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    realizarPedido(scanner);
                    break;
                case 2:
                    verificarStock(scanner);
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }

            System.out.println();
        }

        System.out.println("Gracias por utilizar el sistema de gestión de pedidos de Haku. ¡Hasta luego!");
    }
    private static void realizarPedido(Scanner scanner) {
        System.out.println("Ingrese los detalles del pedido:");

        // Solicitar información del pedido
        System.out.print("Producto (Otaku Anime, Otaku Manga, Otaku Idols, Otaku Militar): ");
        String producto = scanner.nextLine();
        System.out.print("Color (Blanco, Negro): ");
        String color = scanner.nextLine();
        System.out.print("Talla (S, M, L): ");
        String talla = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Detalles de personalización: ");
        String personalizacion = scanner.nextLine();
        System.out.print("Fecha de entrega: ");
        String fechaEntrega = scanner.nextLine();

        // Verificar disponibilidad de productos
        int productoIndex = buscarProducto(producto);
        int colorIndex = buscarColor(color);
        int tallaIndex = buscarTalla(talla);
        if (productoIndex != -1 && colorIndex != -1 && tallaIndex != -1) {
            int stockDisponible = stock[productoIndex][colorIndex][tallaIndex];
            if (stockDisponible >= cantidad) {
                // Confirmar el pedido
                double costoTotal = costos[productoIndex] * cantidad;
                System.out.println("¡Pedido confirmado!");
                System.out.println("Producto: " + producto);
                System.out.println("Color: " + color);
                System.out.println("Talla: " + talla);
                System.out.println("Cantidad: " + cantidad);
                System.out.println("Detalles de personalización: " + personalizacion);
                System.out.println("Fecha de entrega: " + fechaEntrega);
                System.out.println("Costo total: $" + costoTotal);

                // Actualizar stock
                stock[productoIndex][colorIndex][tallaIndex] -= cantidad;
            } else {
                System.out.println("No hay suficiente stock disponible para el producto solicitado.");
            }
        } else {
            System.out.println("El producto, color o talla ingresados no existen.");
        }
    }

    private static void verificarStock(Scanner scanner) {
        System.out.println("Ingrese el producto, color y talla para verificar el stock:");

        // Solicitar información del producto
        System.out.print("Producto (Otaku Anime, Otaku Manga, Otaku Idols, Otaku Militar): ");
        String producto = scanner.nextLine();
        System.out.print("Color (Blanco, Negro): ");
        String color = scanner.nextLine();
        System.out.print("Talla (S, M, L): ");
        String talla = scanner.nextLine();

        // Verificar disponibilidad de productos
        int productoIndex = buscarProducto(producto);
        int colorIndex = buscarColor(color);
        int tallaIndex = buscarTalla(talla);
        if (productoIndex != -1 && colorIndex != -1 && tallaIndex != -1) {
            int stockDisponible = stock[productoIndex][colorIndex][tallaIndex];
            System.out.println("Stock disponible para " + producto + " en color " + color + " y talla " + talla + ": " + stockDisponible);
        } else {
            System.out.println("El producto, color o talla ingresados no existen.");
        }
    }

    private static int buscarProducto(String producto) {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i].equalsIgnoreCase(producto)) {
                return i;
            }
        }
        return -1;
    }

    private static int buscarColor(String color) {
        for (int i = 0; i < colores.length; i++) {
            if (colores[i].equalsIgnoreCase(color)) {
                return i;
            }
        }
        return -1;
    }

    private static int buscarTalla(String talla) {
        for (int i = 0; i < tallas.length; i++) {
            if (tallas[i].equalsIgnoreCase(talla)) {
                return i;
            }
        }
        return -1;
    }
}
