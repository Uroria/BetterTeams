package com.uroria.betterteams;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public final class BetterScoreboard {
    private final Scoreboard scoreboard;
    private final Map<UUID, Team> teams;

    public BetterScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        this.teams = new HashMap<>();
    }

    public void registerPlayer(Player player, int weight) {
        player.setScoreboard(this.scoreboard);
        Team team = getTeam(player, weight);
        this.teams.put(player.getUniqueId(), team);
    }

    public void unregisterPlayer(Player player) {
        Team team = getTeam(player.getUniqueId()).orElse(null);
        this.teams.remove(player.getUniqueId());
        if (team == null) return;
        team.unregister();
    }

    public void setPrefix(Player player, int weight, Component prefix) {
        if (this.teams.containsKey(player.getUniqueId())) return;
        Team team = getTeam(player, weight);
        team.prefix(prefix);
    }

    public void setSuffix(Player player, int weight, Component suffix) {
        if (this.teams.containsKey(player.getUniqueId())) return;
        Team team = getTeam(player, weight);
        team.suffix(suffix);
    }

    public void setColor(Player player, int weight, NamedTextColor color) {
        if (this.teams.containsKey(player.getUniqueId())) return;
        Team team = getTeam(player, weight);
        team.color(color);
    }

    public void setOption(Player player, int weight, Team.Option option, Team.OptionStatus optionStatus) {
        if (this.teams.containsKey(player.getUniqueId())) return;
        Team team = getTeam(player, weight);
        team.setOption(option, optionStatus);
    }

    private Team getTeam(Player player, int weight) {
        Team team = getTeam(player.getUniqueId()).orElse(null);
        if (team == null) {
            team = this.scoreboard.getTeam(weight + player.getName());
            if (team == null) team = this.scoreboard.registerNewTeam(weight + player.getName());
            this.teams.put(player.getUniqueId(), team);
        }
        return team;
    }

    private Optional<Team> getTeam(UUID uuid) {
        return Optional.ofNullable(this.teams.get(uuid));
    }
}
