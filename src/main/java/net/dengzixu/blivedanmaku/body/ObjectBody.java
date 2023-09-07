package net.dengzixu.blivedanmaku.body;

import net.dengzixu.blivedanmaku.enums.Operation;

public record ObjectBody (Operation operation, Object data) implements IBody {
}
