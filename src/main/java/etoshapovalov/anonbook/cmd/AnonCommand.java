package etoshapovalov.anonbook.cmd;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class AnonCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0){
            sender.sendMessage(ChatColor.RED+"Укажите название книги!");
            return true;
        }
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED+"Только для игроков!");
            return true;
        }
        Player player = (Player) sender;
        String bookName = String.join(" ",args);
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if(itemInHand.getType() != Material.WRITABLE_BOOK){
            sender.sendMessage(ChatColor.RED+"Возьмите книгу, которую вы хотите подписать, в руку!");
            return true;
        }
        BookMeta bookMeta = (BookMeta)itemInHand.getItemMeta();
        bookMeta.setAuthor("Аноним");
        bookMeta.setTitle(bookName);
        if(!bookMeta.hasPages()){
            bookMeta.addPages(Component.text(""));
        }
        bookMeta.getPersistentDataContainer().set(new NamespacedKey("anonbook","og_author"), PersistentDataType.STRING, player.getUniqueId().toString());

        itemInHand.setItemMeta(bookMeta);
        itemInHand.setType(Material.WRITTEN_BOOK);
        return true;
    }
}
