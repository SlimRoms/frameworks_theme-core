/*
 * Copyright (C) 2017 SlimRoms Project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.slimroms.themecore;

import android.text.TextUtils;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class Shell {

    public static boolean chmod(File file, int perms) {
        return chmod(file.getAbsolutePath(), perms);
    }

    public static boolean chmod(String path, int perms) {
        CommandOutput output = runCommand("chmod " +
                Integer.toString(perms) + " " + path);
        return output.exitCode == 0 && TextUtils.isEmpty(output.error);
    }

    private static CommandOutput runCommand(String cmd) {
        DataOutputStream os = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("sh");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.flush();
            os.writeBytes("exit\n");
            os.flush();

            InputStream input = process.getInputStream();
            InputStream error = process.getErrorStream();

            String in = IOUtils.toString(input, Charset.defaultCharset());
            String err = IOUtils.toString(error, Charset.defaultCharset());

            if (input != null) {
                input.close();
            }
            if (error != null) {
                error.close();
            }

            process.waitFor();

            CommandOutput output = new CommandOutput(in, err, process.exitValue());
            return output;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return new CommandOutput("", "", 1);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (IOException ignored) {
            }
        }
    }

    private static class CommandOutput {
        String output;
        String error;
        int exitCode;

        private CommandOutput(String output, String error, int exitCode) {
            this.output = output;
            this.error = error;
            this.exitCode = exitCode;
        }
    }
}