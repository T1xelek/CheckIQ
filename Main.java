import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main {

    // AES шифрование
    public static byte[] encryptAES(String input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(input.getBytes());
    }

    // AES расшифровка
    public static String decryptAES(byte[] input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(input);
        return new String(decrypted);
    }

    // XOR операция
    public static String xor(String input, char key) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append((char) (c ^ key));
        }
        return result.toString();
    }

    // Цезарев шифр
    public static String caesarCipher(String input, int shift) {
        StringBuilder result = new StringBuilder();
        for (char character : input.toCharArray()) {
            if (Character.isLetter(character)) {
                char shifted = (char) (character + shift);
                if (Character.isLowerCase(character) && shifted > 'z' ||
                    Character.isUpperCase(character) && shifted > 'Z') {
                    shifted -= 26;
                }
                result.append(shifted);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    // Обратная строка
    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    // Применение hashCode()
    public static String hashCodeToString(String input) {
        return String.valueOf(input.hashCode());
    }

    // Применение StringBuffer
    public static String useStringBuffer(String input) {
        StringBuffer buffer = new StringBuffer(input);
        return buffer.reverse().toString();  // Пример: переворот строки
    }

    public static void main(String[] args) throws Exception {
        String original = "Hello World";

        // 1. XOR операция (перед шифрованием)
        String xored = xor(original, 'K');

        // 2. AES шифрование
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        byte[] aesEncrypted = encryptAES(xored, secretKey);

        // 3. Цезарев шифр (первый раз)
        String caesar1 = caesarCipher(new String(aesEncrypted), 3);

        // 4. Цезарев шифр (второй раз)
        String caesar2 = caesarCipher(caesar1, 5);

        // 5. Обратная строка
        String reversed = reverseString(caesar2);

        // 6. Применение hashCode()
        String hashCodeStr = hashCodeToString(reversed);

        // 7. Применение StringBuffer
        String obfuscated = useStringBuffer(hashCodeStr);

        System.out.println("Original: " + original);
        System.out.println("Obfuscated: " + obfuscated);

        // Для расшифровки выполняем обратные действия
        String bufferReversed = useStringBuffer(obfuscated);
        String decodedHashCodeStr = reverseString(bufferReversed);
        String decaesar2 = caesarCipher(decodedHashCodeStr, -5);
        String decaesar1 = caesarCipher(decaesar2, -3);
        String unxored = xor(decaesar1, 'K');
        byte[] decryptedBytes = decryptAES(unxored.getBytes(), secretKey);
        String deobfuscated = new String(decryptedBytes);

        System.out.println("Deobfuscated: " + deobfuscated);
    }
}
