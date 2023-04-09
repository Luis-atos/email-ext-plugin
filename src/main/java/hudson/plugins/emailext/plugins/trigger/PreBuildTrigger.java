package hudson.plugins.emailext.plugins.trigger;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.EmailTriggerDescriptor;
import hudson.plugins.emailext.plugins.RecipientProvider;
import hudson.plugins.emailext.plugins.recipients.ListRecipientProvider;
import java.util.List;
import org.kohsuke.stapler.DataBoundConstructor;

public class PreBuildTrigger extends EmailTrigger {

    public static final String TRIGGER_NAME = "Before Build";

    @DataBoundConstructor
    public PreBuildTrigger(
            List<RecipientProvider> recipientProviders,
            String recipientList,
            String replyTo,
            String subject,
            String body,
            String attachmentsPattern,
            int attachBuildLog,
            String contentType) {
        super(
                recipientProviders,
                recipientList,
                replyTo,
                subject,
                body,
                attachmentsPattern,
                attachBuildLog,
                contentType);
    }

    @Deprecated
    public PreBuildTrigger(
            boolean sendToList,
            boolean sendToDevs,
            boolean sendToRequester,
            boolean sendToCulprits,
            String recipientList,
            String replyTo,
            String subject,
            String body,
            String attachmentsPattern,
            int attachBuildLog,
            String contentType) {
        super(
                sendToList,
                sendToDevs,
                sendToRequester,
                sendToCulprits,
                recipientList,
                replyTo,
                subject,
                body,
                attachmentsPattern,
                attachBuildLog,
                contentType);
    }

    @Override
    public boolean isPreBuild() {
        return true;
    }

    @Override
    public boolean trigger(AbstractBuild<?, ?> build, TaskListener listener) {
        return true;
    }

    @Extension
    public static final class DescriptorImpl extends EmailTriggerDescriptor {

        public DescriptorImpl() {
            addDefaultRecipientProvider(new ListRecipientProvider());
        }

        @NonNull
        @Override
        public String getDisplayName() {
            return TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createDefault() {
            return _createDefault();
        }
    }
}
