package net.dengzixu.blivedanmaku.body;

import net.dengzixu.blivedanmaku.enums.Operation;

public record StringBody(Operation operation, String data) implements IBody {
}
