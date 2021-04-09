package akkagorn.shared.write

sealed trait AkkagornCommand

object AkkagornCommand {
  sealed trait ManagementCommand extends AkkagornCommand
  sealed trait ActivityFeedCommand extends AkkagornCommand
}
