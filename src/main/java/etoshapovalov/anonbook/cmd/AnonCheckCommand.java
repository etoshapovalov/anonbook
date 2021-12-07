package etoshapovalov.anonbook.cmd;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AnonCheckCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED+"Только для игроков!");
            return true;
        }
        Player player = (Player) sender;
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if(itemInHand.getType() != Material.WRITTEN_BOOK){
            sender.sendMessage(ChatColor.RED+"Возьмите книгу, которую вы хотите проверить, в руку!");
            return true;
        }
        BookMeta bookMeta = (BookMeta) itemInHand.getItemMeta();
        if(bookMeta.getPersistentDataContainer().has(new NamespacedKey("anonbook","og_author"), PersistentDataType.STRING)){
            String authorUUIDString = bookMeta.getPersistentDataContainer().get(new NamespacedKey("anonbook","og_author"),PersistentDataType.STRING);
            UUID authorUUID = UUID.fromString(authorUUIDString);
            OfflinePlayer authorPlayer = Bukkit.getServer().getOfflinePlayer(authorUUID);
            sender.sendMessage("Автор книги: "+authorPlayer.getName());
        } else if (bookMeta.hasAuthor()) {
            sender.sendMessage("Автор книги: "+bookMeta.getAuthor());
        } else {
            sender.sendMessage(ChatColor.RED+"Автор не найден!");
        }
        return true;
    }
}
