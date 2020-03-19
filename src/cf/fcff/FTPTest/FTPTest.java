package cf.fcff.FTPTest;

/**
 * Created by H on 2018/9/6.
 */
public class FTPTest {
    public static void main(String[] args) {
/*
        FTPClient ftp = new FTPClient();
//        FTPClientConfig config = new FTPClientConfig();
//        config.setXXX(YYY); // change required options
        // for example config.setServerTimeZoneId("Pacific/Pitcairn")
//        ftp.configure(config );
        boolean error = false;
        try {
            String server = "ftp.example.com";
            ftp.connect(server);
            System.out.println("Connected to " + server + ".");
            System.out.print(ftp.getReplyString());

            // After connection attempt, you should check the reply code to verify
            // success.
            int reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            // ... transfer files
            ftp.logout();
        } catch (IOException e) {
            error = true;
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    // do nothing
                }
            }
            System.exit(error ? 1 : 0);
        }
*/

        FtpContinueUtilsNoStatic ftpContinueUtilsNoStatic = new FtpContinueUtilsNoStatic();
        try {
            ftpContinueUtilsNoStatic.uploadFile("192.168.2.3", 21, "ftpuser", "huihui", "dir/", "WriteText.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
