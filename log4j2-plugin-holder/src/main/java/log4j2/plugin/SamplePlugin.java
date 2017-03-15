package log4j2.plugin;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Plugin(name = "SamplePlugin", category = "Core", elementType = "layout")
public class SamplePlugin extends AbstractStringLayout {

    private SamplePlugin() {
        super(StandardCharsets.UTF_8);
    }

    @PluginFactory
    public static SamplePlugin create() {
        return new SamplePlugin();
    }

    @Override
    public String toSerializable(LogEvent event) {
        final StringBuilder buf = getStringBuilder();

        buf.append("< Sample Custom Logger >");
        buf.append(event.getMessage().getFormattedMessage());
        buf.append('\n');

        return buf.toString();
    }
}
