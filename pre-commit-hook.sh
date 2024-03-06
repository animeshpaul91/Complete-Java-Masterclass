#!/bin/bash

# The Ticket ID prefix you want to include to your commit messages.
prefix="DLS-"

# Gets the commit message received as parameter and the current branch name.
commitMsg=$1
message=$(cat "$commitMsg")
branchName=$(git symbolic-ref --short HEAD)

# Gets the last part of the branch name feature/DLS-10263_This_is_the_commit_message -> DLS-10263_This_is_the_commit_message
branchName=$(cut -d "/" -f2 <<< "$branchName")

# Checks if the message already includes the ticket ID.
if [[ $message =~ $prefix ]]; then
  echo "🆗 The message already has the ticket ID."
  exit 0
fi

if [[ $branchName =~ $prefix ]]; then
  echo "🧐 The current branch name contains the ticket ID. Trying to add it to the commit message..."

  # Takes from the beginning until the last number before the underscore ((DLS-10263)_This_is_the_commit_message).
  ticketID=$(echo "$branchName" | sed -nE 's,([A-Z]?-?[0-9]+)_.+,\1,p')

  # If the ticket ID is empty, we try to get it from the beginning until the last number before the dash ((DLS-10263)-This-is-the-commit-message).
  if [[ -z $ticketID ]]; then
    ticketID=$(echo "$branchName" | sed -nE 's,([A-Z]?-?[0-9]+)-.+,\1,p')
  fi

  if [[ -z $ticketID ]]; then
      echo "😢 Could not get the ticket ID."
      exit 1
  fi

  echo "🆔 ${ticketID}"
  output="$ticketID $message"
  echo "📩 $output"
  echo -e "$output" > "$1"

else
  echo "😐️ The current branch name does not contain the ticket ID '$ticketID'. Ignoring..."
  exit 0
fi