package configuration.daos;

import io.lettuce.core.TransactionResult;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.ArrayList;
import java.util.function.Supplier;

public class RedisUtility {

    private static final int RETRY_ATTEMPTS = 5;

    public static TransactionResult execWithRetries(RedisCommands<String, String> commander, ArrayList<Supplier> commandList) {

        for (int i = 0; i <= RETRY_ATTEMPTS; i++) {
            for (Supplier command: commandList) {
                command.get();
            }
            TransactionResult result = commander.exec();
            if (!result.isEmpty()) {
                return result;
            }
        }

        return null;

    }




}
