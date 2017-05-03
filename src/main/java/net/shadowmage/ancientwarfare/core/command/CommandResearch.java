package net.shadowmage.ancientwarfare.core.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import net.shadowmage.ancientwarfare.core.research.ResearchGoal;
import net.shadowmage.ancientwarfare.core.research.ResearchTracker;

/**
 * Created by ethan on 03/05/2017.
 */
public class CommandResearch extends CommandBase {

    @Override
    public String getCommandName() {
        return "awresearch";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return server.isSinglePlayer() || super.checkPermission(server, sender);
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "command.aw.research.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

        if(args ==null || args.length<2){
            invalidCommandUsage(sender);
            return;
        }
        String operation = args[0];
        String target = args[1];
        if(operation.equals("add"))
        {
            if(args.length<3){
                invalidCommandUsage(sender);
                return;
            }
            String goal = args[2];
            if(!goal.startsWith("research.")){goal = "research."+goal;}
            ResearchGoal rGoal = ResearchGoal.getGoal(goal);
            if(rGoal==null){
                invalidCommandUsage(sender);
                return;
            }
            ResearchTracker.instance().addResearchFromNotes(sender.getEntityWorld(), target, rGoal.getId());
        }
        else if(operation.equals("remove"))
        {
            if(args.length<3){
                invalidCommandUsage(sender);
                return;
            }
            String goal = args[2];
            if(!goal.startsWith("research.")){goal = "research."+goal;}
            ResearchGoal rGoal = ResearchGoal.getGoal(goal);
            if(rGoal==null){
                invalidCommandUsage(sender);
                return;
            }
            ResearchTracker.instance().removeResearch(sender.getEntityWorld(), target, rGoal.getId());
        }
        else if(operation.equals("fill"))
        {
            ResearchTracker.instance().fillResearch(sender.getEntityWorld(), target);
        }
        else if(operation.equals("clear"))
        {
            ResearchTracker.instance().clearResearch(sender.getEntityWorld(), target);
        }
        else
        {
            invalidCommandUsage(sender);
            return;
        }
        
    }

    public void invalidCommandUsage(ICommandSender sender) {
        sender.addChatMessage(new TextComponentTranslation("command.aw.research.usage"));
    }

}
