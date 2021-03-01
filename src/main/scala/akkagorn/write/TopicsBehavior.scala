package akkagorn.write

class TopicsBehavior(context: ActorContext[AkkagornCommand])
    extends AbstractBehavior[AkkagornCommand](context) {

      override def onMessage(msg: AkkagornCommand): Behavior[TopicsCommand] = 
}